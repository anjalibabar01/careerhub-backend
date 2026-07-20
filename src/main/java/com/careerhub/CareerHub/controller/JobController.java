package com.careerhub.CareerHub.controller;

import com.careerhub.CareerHub.dto.JobResponse;
import com.careerhub.CareerHub.entity.Job;
import com.careerhub.CareerHub.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/jobs")
    public Job saveJob(@RequestBody Job job) {
        return jobService.saveJob(job);
    }

    @GetMapping("/jobs")
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/jobs/{id}")
    public JobResponse getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PutMapping("/jobs/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody Job job) {

        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }

    @GetMapping("/jobs/search/skill")
    public List<JobResponse> searchBySkill(
            @RequestParam String skill) {

        return jobService.searchBySkill(skill);
    }

    @GetMapping("/jobs/search/location")
    public List<JobResponse> searchByLocation(
            @RequestParam String location) {

        return jobService.searchByLocation(location);
    }

    @GetMapping("/jobs/search/company")
    public List<JobResponse> searchByCompany(
            @RequestParam String company) {

        return jobService.searchByCompany(company);
    }

    @GetMapping("/jobs/search/jobtype")
    public List<JobResponse> searchByJobType(
            @RequestParam String jobType) {

        return jobService.searchByJobType(jobType);
    }
}