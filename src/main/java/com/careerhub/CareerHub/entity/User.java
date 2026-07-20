package com.careerhub.CareerHub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String fullName;
    private String email;
    private String password;
    private String role;


    // Recruiter Profile Fields
    private String phone;
    private String location;
    private String companyName;
    private String website;
    private String profileImage;


    // Job Seeker Profile Fields
    private String skills;
    private String education;
    private String experience;
    private String resume;

    private String companyLogoName;

    private String companyLogoPath;


    public User() {
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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



    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }



    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }



    // Recruiter Getters Setters


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



    // Job Seeker Getters Setters


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


}