package com.niua.adapter.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

//@Getter
//@Setter
public class GroupedData {
    public GroupedData() {
    }

    public String getGroupBy() {
        return groupBy;
    }

    public GroupedData(String groupBy, List<Bucket> buckets) {
        this.groupBy = groupBy;
        this.buckets = buckets;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    public List<Bucket> getBuckets() {
        return buckets;
    }

    private String groupBy;
    private List<Bucket> buckets;
}
