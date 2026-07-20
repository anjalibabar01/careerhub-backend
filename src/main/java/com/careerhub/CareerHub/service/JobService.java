package com.careerhub.CareerHub.service;

import com.careerhub.CareerHub.dto.JobResponse;
import com.careerhub.CareerHub.entity.Job;
import com.careerhub.CareerHub.entity.User;
import com.careerhub.CareerHub.repository.JobRepository;
import com.careerhub.CareerHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public Job saveJob(Job job) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User recruiter = userRepository.findByEmail(email);

        if (recruiter == null) {
            throw new RuntimeException("Recruiter not found");
        }

        job.setRecruiter(recruiter);

        job.setCompanyName(recruiter.getCompanyName());

        job.setPostedDate(LocalDate.now());

        return jobRepository.save(job);
    }

    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);

        if (job == null) {
            return null;
        }

        return convertToResponse(job);
    }

    public String deleteJob(Long id) {

        jobRepository.deleteById(id);

        return "Job deleted successfully";
    }

    public Job updateJob(Long id, Job updatedJob) {

        Job existingJob = jobRepository.findById(id).orElse(null);

        if (existingJob == null) {
            return null;
        }

        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setRequiredSkills(updatedJob.getRequiredSkills());
        existingJob.setJobType(updatedJob.getJobType());

        return jobRepository.save(existingJob);
    }

    public List<JobResponse> searchBySkill(String skill) {

        return jobRepository
                .findByRequiredSkillsContainingIgnoreCase(skill)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<JobResponse> searchByLocation(String location) {

        return jobRepository
                .findByLocationContainingIgnoreCase(location)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<JobResponse> searchByCompany(String company) {

        return jobRepository
                .findByCompanyNameContainingIgnoreCase(company)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<JobResponse> searchByJobType(String jobType) {

        return jobRepository
                .findByJobTypeContainingIgnoreCase(jobType)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private JobResponse convertToResponse(Job job) {

        User recruiter = job.getRecruiter();

        String companyLogoName = null;

        if (recruiter != null) {
            companyLogoName = recruiter.getCompanyLogoName();
        }

        return new JobResponse(
                job.getId(),
                job.getJobTitle(),
                job.getCompanyName(),
                companyLogoName,
                job.getLocation(),
                job.getSalary(),
                job.getDescription(),
                job.getRequiredSkills(),
                job.getJobType(),
                job.getPostedDate()
        );
    }

}