package com.careerhub.CareerHub.dto;

import java.time.LocalDate;

public class JobResponse {

    private Long id;
    private String jobTitle;
    private String companyName;
    private String companyLogoName;
    private String location;
    private Double salary;
    private String description;
    private String requiredSkills;
    private String jobType;
    private LocalDate postedDate;

    public JobResponse() {
    }

    public JobResponse(
            Long id,
            String jobTitle,
            String companyName,
            String companyLogoName,
            String location,
            Double salary,
            String description,
            String requiredSkills,
            String jobType,
            LocalDate postedDate
    ) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.companyLogoName = companyLogoName;
        this.location = location;
        this.salary = salary;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.jobType = jobType;
        this.postedDate = postedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogoName() {
        return companyLogoName;
    }

    public void setCompanyLogoName(String companyLogoName) {
        this.companyLogoName = companyLogoName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }
}