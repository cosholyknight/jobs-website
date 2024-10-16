package com.setalite.jobbackend.repository;

import com.setalite.jobbackend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
    public boolean existsByName(String name);
}