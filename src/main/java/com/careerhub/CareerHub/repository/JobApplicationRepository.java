package com.careerhub.CareerHub.repository;

import com.careerhub.CareerHub.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByJobId(Long jobId);

    List<JobApplication> findByApplicantEmail(String applicantEmail);
    long count();

    long countByStatus(String status);

}