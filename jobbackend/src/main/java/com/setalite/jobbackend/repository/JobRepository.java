package com.setalite.jobbackend.repository;

import com.setalite.jobbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
