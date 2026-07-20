package com.careerhub.CareerHub.controller;

import com.careerhub.CareerHub.entity.JobApplication;
import com.careerhub.CareerHub.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class JobApplicationController {

    @Autowired
    private JobApplicationService applicationService;

    // Apply for Job
    @PostMapping("/apply/{jobId}")
    public JobApplication applyForJob(
            @PathVariable Long jobId,
            @RequestBody JobApplication application) {

        return applicationService.applyForJob(
                jobId,
                application
        );
    }

    // Get All Applications (Recruiter)
    @GetMapping("/applications")
    public List<JobApplication> getAllApplications() {

        return applicationService.getAllApplications();

    }

    // Get Applications By Job
    @GetMapping("/applications/job/{jobId}")
    public List<JobApplication> getByJob(
            @PathVariable Long jobId) {

        return applicationService.getApplicationsByJob(
                jobId
        );
    }

    // Get My Applications (Job Seeker)
    @GetMapping("/applications/my")
    public List<JobApplication> getMyApplications(
            @RequestParam String email) {

        return applicationService.getApplicationsByEmail(
                email
        );
    }

    // Update Application Status
    @PutMapping("/applications/{id}/status")
    public JobApplication updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return applicationService.updateStatus(
                id,
                status
        );
    }

    // Upload Resume
    @PostMapping(
            value = "/applications/{id}/resume",
            consumes = "multipart/form-data"
    )
    public JobApplication uploadResume(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return applicationService.uploadResume(
                id,
                file
        );
    }

    // Download Resume
    @GetMapping("/applications/{id}/resume")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable Long id)
            throws IOException {

        Resource resource =
                applicationService.downloadResume(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename() +
                                "\""
                )
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        "application/pdf"
                )
                .body(resource);
    }
}