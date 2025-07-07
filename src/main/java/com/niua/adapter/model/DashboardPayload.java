package com.niua.adapter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardPayload {
    private String date;
    private String module;
    private String ward;
    private String ulb;
    private String region;
    private String state;
    private Metrics metrics;

    // Getters and Setters
}

