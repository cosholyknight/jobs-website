package com.setalite.jobbackend.mapper;

import com.setalite.jobbackend.dto.request.JobCreationRequest;
import com.setalite.jobbackend.dto.request.JobUpdateRequest;
import com.setalite.jobbackend.dto.response.JobResponse;
import com.setalite.jobbackend.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job toJob(JobCreationRequest request);
    void updateJob(@MappingTarget Job job, JobUpdateRequest request);

    JobResponse toJobResponse(Job job);

}
