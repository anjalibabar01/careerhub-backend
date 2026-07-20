package com.careerhub.CareerHub.controller;

import com.careerhub.CareerHub.entity.SavedJob;
import com.careerhub.CareerHub.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @PostMapping("/saved-jobs/{jobId}")
    public SavedJob saveJob(
            @PathVariable Long jobId,
            @RequestParam String email){

        return savedJobService
                .saveJob(
                        jobId,
                        email
                );
    }

    @GetMapping("/saved-jobs/my")
    public List<SavedJob> getSavedJobs(
            @RequestParam String email){

        return savedJobService
                .getSavedJobs(
                        email
                );
    }

    @DeleteMapping("/saved-jobs/{id}")
    public String deleteSavedJob(
            @PathVariable Long id){

        return savedJobService
                .deleteSavedJob(
                        id
                );
    }
}