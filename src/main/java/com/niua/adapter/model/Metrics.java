package com.niua.adapter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Metrics {
    private int assessments;
    private int todaysTotalApplications;
    private int todaysClosedApplications;
    private int noOfPropertiesPaidToday;
    private int todaysApprovedApplications;
    private int todaysApprovedApplicationsWithinSLA;
    private int pendingApplicationsBeyondTimeline;
    private int avgDaysForApplicationApproval;
    private int StipulatedDays;

    private List<GroupedData> todaysMovedApplications;
    private List<GroupedData> propertiesRegistered;
    private List<GroupedData> assessedProperties;
    private List<GroupedData> transactions;
    private List<GroupedData> todaysCollection;
    private List<GroupedData> propertyTax;
    private List<GroupedData> cess;
    private List<GroupedData> rebate;
    private List<GroupedData> penalty;
    private List<GroupedData> interest;
}
