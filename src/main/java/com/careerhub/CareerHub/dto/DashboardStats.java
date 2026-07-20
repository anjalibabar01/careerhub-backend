package com.careerhub.CareerHub.dto;

public class DashboardStats {

    private long totalJobs;
    private long totalApplications;
    private long shortlisted;
    private long hired;

    public DashboardStats() {
    }

    public DashboardStats(long totalJobs,
                          long totalApplications,
                          long shortlisted,
                          long hired) {

        this.totalJobs = totalJobs;
        this.totalApplications = totalApplications;
        this.shortlisted = shortlisted;
        this.hired = hired;
    }

    public long getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(long totalJobs) {
        this.totalJobs = totalJobs;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getShortlisted() {
        return shortlisted;
    }

    public void setShortlisted(long shortlisted) {
        this.shortlisted = shortlisted;
    }

    public long getHired() {
        return hired;
    }

    public void setHired(long hired) {
        this.hired = hired;
    }
}