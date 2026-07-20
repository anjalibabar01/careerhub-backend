package com.careerhub.CareerHub.repository;

import com.careerhub.CareerHub.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedJobRepository
        extends JpaRepository<SavedJob, Long> {

    List<SavedJob> findByUserEmail(
            String userEmail
    );
}