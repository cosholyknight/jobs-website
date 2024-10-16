package com.setalite.jobbackend.repository;

import com.setalite.jobbackend.dto.request.JobUpdateRequest;
import com.setalite.jobbackend.entity.Job;
import org.apache.catalina.User;
import org.mapstruct.MappingTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {

}
