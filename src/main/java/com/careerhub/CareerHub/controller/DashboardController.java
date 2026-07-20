package com.careerhub.CareerHub.controller;

import com.careerhub.CareerHub.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard/recruiter")
    public Map<String, Object> recruiterDashboard() {

        return dashboardService.getRecruiterDashboard();
    }

    @GetMapping("/dashboard/jobseeker")
    public Map<String, Object> jobSeekerDashboard(
            @RequestParam String email) {

        return dashboardService.getJobSeekerDashboard(email);
    }
}