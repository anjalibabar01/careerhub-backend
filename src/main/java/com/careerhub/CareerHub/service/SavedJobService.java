package com.careerhub.CareerHub.service;

import com.careerhub.CareerHub.entity.Job;
import com.careerhub.CareerHub.entity.SavedJob;
import com.careerhub.CareerHub.repository.JobRepository;
import com.careerhub.CareerHub.repository.SavedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Autowired
    private JobRepository jobRepository;

    public SavedJob saveJob(
            Long jobId,
            String email){

        Job job =
                jobRepository
                        .findById(jobId)
                        .orElse(null);

        if(job == null){
            return null;
        }

        SavedJob savedJob =
                new SavedJob();

        savedJob.setUserEmail(
                email
        );

        savedJob.setJob(
                job
        );

        return savedJobRepository
                .save(savedJob);
    }

    public List<SavedJob> getSavedJobs(
            String email){

        return savedJobRepository
                .findByUserEmail(email);
    }

    public String deleteSavedJob(
            Long id){

        savedJobRepository
                .deleteById(id);

        return "Saved job removed";
    }
}