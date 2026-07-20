package com.careerhub.CareerHub.dto;

public class ProfileResponse {

    private String fullName;
    private String email;
    private String role;

    // Common Profile Fields
    private String phone;
    private String location;

    // Recruiter Fields
    private String companyName;
    private String website;
    private String profileImage;

    // Company Logo
    private String companyLogoName;
    private String companyLogoPath;

    // Job Seeker Fields
    private String skills;
    private String education;
    private String experience;
    private String resume;

    public ProfileResponse() {
    }

    public ProfileResponse(
            String fullName,
            String email,
            String role,
            String phone,
            String location,
            String companyName,
            String website,
            String profileImage,
            String companyLogoName,
            String companyLogoPath,
            String skills,
            String education,
            String experience,
            String resume
    ) {

        this.fullName = fullName;
        this.email = email;
        this.role = role;

        this.phone = phone;
        this.location = location;

        this.companyName = companyName;
        this.website = website;
        this.profileImage = profileImage;

        this.companyLogoName = companyLogoName;
        this.companyLogoPath = companyLogoPath;

        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.resume = resume;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCompanyLogoName() {
        return companyLogoName;
    }

    public void setCompanyLogoName(String companyLogoName) {
        this.companyLogoName = companyLogoName;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}