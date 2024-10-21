package com.setalite.jobbackend.service;

import com.setalite.jobbackend.dto.request.JobCreationRequest;
import com.setalite.jobbackend.dto.request.JobUpdateRequest;
import com.setalite.jobbackend.dto.response.JobResponse;
import com.setalite.jobbackend.entity.Company;
import com.setalite.jobbackend.entity.Job;
import com.setalite.jobbackend.exception.AppException;
import com.setalite.jobbackend.exception.ErrorCode;
import com.setalite.jobbackend.mapper.CompanyMapper;
import com.setalite.jobbackend.mapper.JobMapper;
import com.setalite.jobbackend.repository.CompanyRepository;
import com.setalite.jobbackend.repository.JobRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobService {
    JobRepository jobRepository;
    JobMapper jobMapper;
    CompanyService companyService;

    public JobResponse createJob(JobCreationRequest request) {
        Job job = jobMapper.toJob(request);
        Company company = companyService.getCompany(request.getCompanyId());
        job.setCompany(company);
        return jobMapper.toJobResponse(jobRepository.save(job));
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toJobResponse)
                .toList();
    }

    public JobResponse updateJob(JobUpdateRequest request, String id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));
        jobMapper.updateJob(job, request);
        return jobMapper.toJobResponse(jobRepository.save(job));
    }

    public JobResponse getJobById(String id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));
        return jobMapper.toJobResponse(job);
    }

    public void deleteJobById(String id) {

        jobRepository.deleteById(id);
    }
}
