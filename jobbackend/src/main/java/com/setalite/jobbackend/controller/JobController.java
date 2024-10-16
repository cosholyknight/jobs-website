package com.setalite.jobbackend.controller;

import com.setalite.jobbackend.dto.request.JobCreationRequest;
import com.setalite.jobbackend.dto.request.JobUpdateRequest;
import com.setalite.jobbackend.dto.response.ApiResponse;
import com.setalite.jobbackend.dto.response.JobResponse;
import com.setalite.jobbackend.entity.Job;
import com.setalite.jobbackend.service.JobService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobController {
    JobService jobService;

    @PostMapping
    ApiResponse<JobResponse> createJob(@RequestBody JobCreationRequest request) {
        return ApiResponse.<JobResponse>builder()
                .result(jobService.createJob(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<JobResponse>> getAllJobs() {
        return ApiResponse.<List<JobResponse>>builder()
                .result(jobService.getAllJobs())
                .build();
    }

    @GetMapping("/{job-id}")
    ApiResponse<JobResponse> getJobById(@PathVariable("job-id") String id) {
        return ApiResponse.<JobResponse>builder()
                .result(jobService.getJobById(id))
                .build();
    }

    @DeleteMapping("/{job-id}")
    ApiResponse<Void> deleteJobById(@PathVariable("job-id") String id) {
        jobService.deleteJobById(id);
        return ApiResponse.<Void>builder()
                .message("Job with id " + id + " deleted.")
                .build();
    }

    @PutMapping("/{job-id}")
    ApiResponse<JobResponse> updateJob(
            @PathVariable ("job-id") String id,
            @RequestBody JobUpdateRequest request
            ) {
        return ApiResponse.<JobResponse>builder()
                .result(jobService.updateJob(request, id))
                .build();
    }
}
