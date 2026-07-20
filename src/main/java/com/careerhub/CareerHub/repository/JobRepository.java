package com.careerhub.CareerHub.repository;

import com.careerhub.CareerHub.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository
        extends JpaRepository<Job, Long> {

    List<Job> findByRequiredSkillsContainingIgnoreCase(
            String skill
    );

    List<Job> findByLocationContainingIgnoreCase(
            String location
    );

    List<Job> findByCompanyNameContainingIgnoreCase(
            String companyName
    );

    List<Job> findByJobTypeContainingIgnoreCase(
            String jobType
    );
    long count();

}