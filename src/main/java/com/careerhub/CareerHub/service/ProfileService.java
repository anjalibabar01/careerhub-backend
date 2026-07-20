package com.careerhub.CareerHub.service;

import com.careerhub.CareerHub.dto.ProfileResponse;
import com.careerhub.CareerHub.entity.User;
import com.careerhub.CareerHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ProfileResponse getProfile() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return new ProfileResponse(

                user.getFullName(),
                user.getEmail(),
                user.getRole(),

                user.getPhone(),
                user.getLocation(),

                user.getCompanyName(),
                user.getWebsite(),
                user.getProfileImage(),

                user.getCompanyLogoName(),
                user.getCompanyLogoPath(),

                user.getSkills(),
                user.getEducation(),
                user.getExperience(),
                user.getResume()
        );
    }

    public ProfileResponse updateProfile(ProfileResponse profile) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Common Fields
        user.setFullName(profile.getFullName());
        user.setPhone(profile.getPhone());
        user.setLocation(profile.getLocation());

        // Recruiter Fields
        user.setCompanyName(profile.getCompanyName());
        user.setWebsite(profile.getWebsite());
        user.setProfileImage(profile.getProfileImage());

        // Job Seeker Fields
        user.setSkills(profile.getSkills());
        user.setEducation(profile.getEducation());
        user.setExperience(profile.getExperience());
        user.setResume(profile.getResume());

        userRepository.save(user);

        return new ProfileResponse(

                user.getFullName(),
                user.getEmail(),
                user.getRole(),

                user.getPhone(),
                user.getLocation(),

                user.getCompanyName(),
                user.getWebsite(),
                user.getProfileImage(),

                user.getCompanyLogoName(),
                user.getCompanyLogoPath(),

                user.getSkills(),
                user.getEducation(),
                user.getExperience(),
                user.getResume()
        );
    }

    public ProfileResponse uploadCompanyLogo(MultipartFile file)
            throws IOException {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String uploadDir =
                System.getProperty("user.dir")
                        + "/uploads/logos/";

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName =
                UUID.randomUUID()
                        + "_"
                        + file.getOriginalFilename();

        File destination =
                new File(uploadDir + fileName);

        file.transferTo(destination);

        user.setCompanyLogoName(fileName);
        user.setCompanyLogoPath(destination.getAbsolutePath());

        userRepository.save(user);

        return new ProfileResponse(

                user.getFullName(),
                user.getEmail(),
                user.getRole(),

                user.getPhone(),
                user.getLocation(),

                user.getCompanyName(),
                user.getWebsite(),
                user.getProfileImage(),

                user.getCompanyLogoName(),
                user.getCompanyLogoPath(),

                user.getSkills(),
                user.getEducation(),
                user.getExperience(),
                user.getResume()
        );
    }
}