package com.setalite.jobbackend.service;

import com.setalite.jobbackend.dto.request.CompanyCreationRequest;
import com.setalite.jobbackend.dto.request.JobCreationRequest;
import com.setalite.jobbackend.dto.response.CompanyResponse;
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
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyService {
    CompanyRepository companyRepository;
    CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyResponse createCompany(CompanyCreationRequest request) {
        Company company = companyMapper.toCompany(request);
        return companyMapper.toCompanyResponse(companyRepository.save(company));
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toCompanyResponse)
                .toList();
    }

    public CompanyResponse getCompanyById(String id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED));
        return companyMapper.toCompanyResponse(company);
    }

    public void deleteCompanyById(String id) {
        companyRepository.deleteById(id);
    }
}
