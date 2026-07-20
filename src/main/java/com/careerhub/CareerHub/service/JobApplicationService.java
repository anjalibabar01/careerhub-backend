package com.careerhub.CareerHub.service;

import com.careerhub.CareerHub.entity.Job;
import com.careerhub.CareerHub.entity.JobApplication;
import com.careerhub.CareerHub.repository.JobApplicationRepository;
import com.careerhub.CareerHub.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    public JobApplication applyForJob(
            Long jobId,
            JobApplication application) {

        Job job = jobRepository
                .findById(jobId)
                .orElse(null);

        if(job == null){
            return null;
        }

        application.setJob(job);
        application.setAppliedDate(LocalDate.now());
        application.setStatus("APPLIED");

        return applicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsByJob(
            Long jobId){

        return applicationRepository
                .findByJobId(jobId);
    }

    public List<JobApplication> getAllApplications() {

        return applicationRepository.findAll();

    }

    public List<JobApplication> getApplicationsByEmail(
            String email){

        return applicationRepository
                .findByApplicantEmail(email);
    }

    public JobApplication updateStatus(
            Long applicationId,
            String status){

        JobApplication application=
                applicationRepository
                        .findById(applicationId)
                        .orElse(null);

        if(application!=null){

            application.setStatus(status);

            return applicationRepository
                    .save(application);
        }

        return null;
    }

    public JobApplication uploadResume(
            Long applicationId,
            MultipartFile file)
            throws IOException {

        JobApplication application =
                applicationRepository
                        .findById(applicationId)
                        .orElse(null);

        if(application==null){
            throw new RuntimeException(
                    "Application not found"
            );
        }

        String uploadDir =
                System.getProperty("user.dir")
                        + "/uploads/";

        File directory =
                new File(uploadDir);

        if(!directory.exists()){
            directory.mkdirs();
        }

        String fileName=
                UUID.randomUUID()
                        + "_"
                        + file.getOriginalFilename();

        File destination=
                new File(
                        uploadDir+fileName
                );

        file.transferTo(destination);

        application.setResumeName(
                fileName
        );

        application.setResumePath(
                destination.getAbsolutePath()
        );

        return applicationRepository
                .save(application);
    }

    public Resource downloadResume(
            Long applicationId)
            throws IOException {

        JobApplication application =
                applicationRepository
                        .findById(applicationId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Application not found"
                                )
                        );

        Path path =
                Path.of(
                        application.getResumePath()
                );

        Resource resource =
                new UrlResource(
                        path.toUri()
                );

        if(resource.exists()){
            return resource;
        }

        throw new RuntimeException(
                "Resume file not found"
        );
    }
}