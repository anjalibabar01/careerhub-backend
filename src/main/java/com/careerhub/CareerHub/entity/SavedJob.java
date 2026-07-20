package com.careerhub.CareerHub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_jobs")
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    public SavedJob() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(
            String userEmail) {

        this.userEmail = userEmail;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(
            Job job) {

        this.job = job;
    }
}