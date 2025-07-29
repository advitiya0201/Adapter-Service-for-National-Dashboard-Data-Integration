package com.niua.adapter.model;

//@Getter
//@Setter
public class DashboardPayload {
    private String date;
    private String module;
    private String ward;
    private String ulb;
    private String region;

    public DashboardPayload(String date, String module, String ward, String ulb, String region, String state, Metrics metrics) {
        this.date = date;
        this.module = module;
        this.ward = ward;
        this.ulb = ulb;
        this.region = region;
        this.state = state;
        this.metrics = metrics;
    }

    private String state;
    private Metrics metrics;

    public DashboardPayload() {
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setUlb(String ulb) {
        this.ulb = ulb;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public String getDate() {
        return date;
    }

    public String getModule() {
        return module;
    }

    public String getWard() {
        return ward;
    }

    public String getUlb() {
        return ulb;
    }

    public String getRegion() {
        return region;
    }

    public String getState() {
        return state;
    }

    public Metrics getMetrics() {
        return metrics;
    }
// Getters and Setters
}

