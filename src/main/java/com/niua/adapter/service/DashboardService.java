package com.niua.adapter.service;

import com.niua.adapter.model.Bucket;
import com.niua.adapter.model.DashboardPayload;
import com.niua.adapter.model.GroupedData;
import com.niua.adapter.model.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.addAll;

@Service
public class DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DashboardPayload fetchDashboardData() {
        DashboardPayload payload = new DashboardPayload();
        Metrics metrics = new Metrics();

        List<GroupedData> movedApplications = getTodaysMovedApplications();
        metrics.setTodaysMovedApplications(movedApplications);
        metrics.setPropertiesRegistered(getPropertiesRegisteredByFinancialYear());
        metrics.setAssessedProperties(getAssessedProperties());
        metrics.setTransactions(getTransactions());
        metrics.setTodaysCollection(getTodaysCollections());
        metrics.setPropertyTax(getPropertyTax());
        metrics.setCess(getCess());
        metrics.setRebate(getRebate());
        metrics.setPenalty(getPenalty());
        metrics.setInterest(getInterest());

        payload.setMetrics(metrics);
        payload.setDate(java.time.LocalDate.now().toString()); // just an example

        // other fields like module, ward, ulb, etc. can be populated here too

        return payload;
    }

    private List<GroupedData> getTodaysMovedApplications() {
        String sql = """
            SELECT action, COUNT(*) as count
            FROM eg_wf_processinstance_v2
            WHERE tenantid = 'pg.citya'
              AND modulename = 'PT'
              AND TO_DATE(TO_CHAR(TO_TIMESTAMP(createdtime / 1000), 'YYYY-MM-DD'), 'YYYY-MM-DD') = '2025-07-03'
            GROUP BY action
        """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("action"));
            bucket.setValue(rs.getInt("count"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("action");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getPropertiesRegisteredByFinancialYear() {
        String sql = """
        SELECT
            CASE
                WHEN EXTRACT(MONTH FROM reg_date) >= 4
                THEN TO_CHAR(reg_date, 'YYYY') || '-' || TO_CHAR(reg_date + INTERVAL '1 year', 'YY')
                ELSE TO_CHAR(reg_date - INTERVAL '1 year', 'YYYY') || '-' || TO_CHAR(reg_date, 'YY')
            END AS financial_year,
            COUNT(*) AS value
        FROM (
            SELECT
                TO_TIMESTAMP(createdtime / 1000)::date AS reg_date
            FROM eg_pt_property
            WHERE creationreason = 'CREATE'
              AND tenantid = 'pg.citya'
        ) t
        GROUP BY financial_year
        ORDER BY financial_year
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("financial_year"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("financial_year");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getAssessedProperties() {
        String sql = """
        SELECT 
            p.usagecategory AS name,
            COUNT(DISTINCT a.propertyid) AS value
        FROM eg_pt_asmt_assessment a
        JOIN eg_pt_property p ON a.propertyid = p.propertyid
        GROUP BY p.usagecategory
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getTransactions() {
        String sql = """
        SELECT
            pp.usagecategory AS name,
            COUNT(*) AS value
           FROM\s
                egcl_paymentdetail epd
            JOIN\s
                egcl_bill eb ON epd.billid = eb.id
               JOIN\s
                eg_pt_property pp ON eb.consumercode = pp.propertyid
        WHERE\s
                epd.businessservice = 'PT'
        GROUP BY\s
                pp.usagecategory;
        
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getTodaysCollections() {
        String sql = """
        SELECT\s
          'usageCategory' AS "groupBy",
          p.usagecategory AS name,
          COUNT(*) AS value
        FROM egcl_payment ep
        JOIN egcl_paymentdetail epd ON ep.id = epd.paymentid
        JOIN egcl_bill eb ON eb.id = epd.billid
        JOIN eg_pt_property p ON eb.consumercode = p.propertyid
        WHERE ep.transactiondate >= extract(epoch FROM DATE '2025-07-03') * 1000
        GROUP BY p.usagecategory;
    """;

        String paymentChannelSql = """
        SELECT\s
          'paymentChannelType' AS "groupBy",
          CASE\s
            WHEN ep.paymentmode = 'ONLINE' THEN 'Digital'
            WHEN ep.paymentmode = 'CASH' THEN 'Non Digital'
          END AS name,
          SUM(ep.totalamountpaid) AS value
        FROM egcl_payment ep
        WHERE ep.transactiondate >= extract(epoch FROM DATE '2025-07-03') * 1000
        GROUP BY name;    
        """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        List<Bucket> buckets1= jdbcTemplate.query(paymentChannelSql, (rs, rowNum) -> {
                    Bucket bucket = new Bucket();
                    bucket.setName(rs.getString("name"));
                    bucket.setValue(rs.getInt("value"));
                    return bucket;
                });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        GroupedData groupedData1 = new GroupedData();
        groupedData1.setGroupBy("paymentchanneltype");
        groupedData1.setBuckets(buckets1);

        List<GroupedData> list = List.of(groupedData, groupedData1);
        return list;
    }

    private List<GroupedData> getPropertyTax() {
        String sql = """
        -- SELECT * FROM public.egbs_demanddetail_v1
        -- ORDER BY id ASC, tenantid ASC\s
        
        SELECT\s
            p.usagecategory AS name,
            COUNT(dd.id) AS value
        FROM egbs_demanddetail_v1 dd
        JOIN egbs_demand_v1 d ON dd.demandid = d.id
        JOIN eg_pt_property p ON d.consumercode = p.propertyid
        WHERE\s
            dd.taxheadcode = 'PT_TAX'
            AND d.businessservice = 'PT'
        GROUP BY p.usagecategory;
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getCess() {
        String sql = """
        SELECT\s
            p.usagecategory AS name,
            COUNT(dd.id) AS value
        FROM egbs_demanddetail_v1 dd
        JOIN egbs_demand_v1 d ON dd.demandid = d.id
        JOIN eg_pt_property p ON d.consumercode = p.propertyid
        WHERE\s
            dd.taxheadcode IN ('PT_FIRE_CESS', 'PT_CANCER_CESS') \s
            AND d.businessservice = 'PT'
        GROUP BY p.usagecategory;
        
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getRebate() {
        String sql = """
        SELECT\s
            p.usagecategory AS name,
            COUNT(dd.id) AS value
        FROM egbs_demanddetail_v1 dd
        JOIN egbs_demand_v1 d ON dd.demandid = d.id
        JOIN eg_pt_property p ON d.consumercode = p.propertyid
        WHERE\s
            dd.taxheadcode IN ('PT_TIME_REBATE', 'PT_ADHOC_REBATE') \s
            AND d.businessservice = 'PT'
        GROUP BY p.usagecategory;
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getPenalty() {
        String sql = """
        SELECT\s
            p.usagecategory AS name,
            COUNT(dd.id) AS value
        FROM egbs_demanddetail_v1 dd
        JOIN egbs_demand_v1 d ON dd.demandid = d.id
        JOIN eg_pt_property p ON d.consumercode = p.propertyid
        WHERE\s
            dd.taxheadcode IN ('PT_TIME_PENALTY', 'PT_ADHOC_PENALTY') \s
            AND d.businessservice = 'PT'
        GROUP BY p.usagecategory;
        
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }

    private List<GroupedData> getInterest() {
        String sql = """
        SELECT\s
            p.usagecategory AS name,
            COUNT(dd.id) AS value
        FROM egbs_demanddetail_v1 dd
        JOIN egbs_demand_v1 d ON dd.demandid = d.id
        JOIN eg_pt_property p ON d.consumercode = p.propertyid
        WHERE\s
            dd.taxheadcode IN ('PT_TIME_INTEREST') \s
            AND d.businessservice = 'PT'
        GROUP BY p.usagecategory;
    """;

        List<Bucket> buckets = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bucket bucket = new Bucket();
            bucket.setName(rs.getString("name"));
            bucket.setValue(rs.getInt("value"));
            return bucket;
        });

        GroupedData groupedData = new GroupedData();
        groupedData.setGroupBy("usagecategory");
        groupedData.setBuckets(buckets);

        return List.of(groupedData);
    }






}
