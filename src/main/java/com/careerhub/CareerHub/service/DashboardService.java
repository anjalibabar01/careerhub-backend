package com.careerhub.CareerHub.service;

import com.careerhub.CareerHub.repository.JobApplicationRepository;
import com.careerhub.CareerHub.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository applicationRepository;

    public Map<String, Object> getRecruiterDashboard() {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put(
                "totalJobs",
                jobRepository.count()
        );

        dashboard.put(
                "totalApplications",
                applicationRepository.count()
        );

        dashboard.put(
                "shortlisted",
                applicationRepository.countByStatus("SHORTLISTED")
        );

        dashboard.put(
                "hired",
                applicationRepository.countByStatus("HIRED")
        );

        return dashboard;
    }

    public Map<String, Object> getJobSeekerDashboard(String email) {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put(
                "appliedJobs",
                applicationRepository
                        .findByApplicantEmail(email)
                        .size()
        );

        dashboard.put(
                "shortlisted",
                applicationRepository
                        .findByApplicantEmail(email)
                        .stream()
                        .filter(app -> "SHORTLISTED".equals(app.getStatus()))
                        .count()
        );

        dashboard.put(
                "hired",
                applicationRepository
                        .findByApplicantEmail(email)
                        .stream()
                        .filter(app -> "HIRED".equals(app.getStatus()))
                        .count()
        );

        return dashboard;
    }
}