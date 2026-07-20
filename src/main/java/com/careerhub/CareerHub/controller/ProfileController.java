package com.careerhub.CareerHub.controller;

import com.careerhub.CareerHub.dto.ProfileResponse;
import com.careerhub.CareerHub.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ProfileResponse getProfile() {
        return profileService.getProfile();
    }

    @PutMapping
    public ProfileResponse updateProfile(
            @RequestBody ProfileResponse profile) {

        return profileService.updateProfile(profile);
    }

    @PostMapping("/logo")
    public ProfileResponse uploadCompanyLogo(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return profileService.uploadCompanyLogo(file);
    }
}