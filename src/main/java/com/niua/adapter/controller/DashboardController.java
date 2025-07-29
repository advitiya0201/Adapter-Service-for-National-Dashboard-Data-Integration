package com.niua.adapter.controller;

import com.niua.adapter.model.DashboardPayload;
import com.niua.adapter.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardPayload getDashboardPayload() {
        return dashboardService.fetchDashboardData();
    }
}
