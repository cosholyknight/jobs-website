package com.setalite.jobbackend.service;

import com.setalite.jobbackend.controller.CompanyController;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Slf4j
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

    public CompanyResponse createCompany(CompanyCreationRequest request) throws IOException {
        if (companyRepository.existsByName(request.getName())) {
            return companyMapper.toCompanyResponse(companyMapper.toCompany(request));
        }
        Company company = companyMapper.toCompany(request);
        MultipartFile logoFile = request.getLogoFile();
        String logoPath = null;

        // Lưu file vào thư mục resources/static
        if (logoFile != null && !logoFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(logoFile.getOriginalFilename()));
            String uploadDir = "jobbackend/src/main/resources/static/company-logos/";

            Path uploadPath = Paths.get(uploadDir);
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            InputStream inputStream = logoFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            logoPath = "/company-logos/" + fileName;
            log.info(logoPath);
        }
        company.setLogoPath(logoPath);
        return companyMapper.toCompanyResponse(companyRepository.save(company));
    }

    public Company createCompanyInJob(CompanyCreationRequest request) {
        if (companyRepository.existsByName(request.getName())) {
            return companyRepository.findByName(request.getName())
                    .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED));
        }
        Company company = companyMapper.toCompany(request);
        return companyRepository.save(company);
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

    public Company getCompany(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED));
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED));
    }

    public void deleteCompanyById(String id) {
        companyRepository.deleteById(id);
    }
}
