package com.setalite.jobbackend.controller;

import com.setalite.jobbackend.dto.request.CompanyCreationRequest;
import com.setalite.jobbackend.dto.request.JobCreationRequest;
import com.setalite.jobbackend.dto.response.ApiResponse;
import com.setalite.jobbackend.dto.response.CompanyResponse;
import com.setalite.jobbackend.dto.response.JobResponse;
import com.setalite.jobbackend.service.CompanyService;
import com.setalite.jobbackend.service.JobService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    CompanyService companyService;

    @PostMapping
    ApiResponse<CompanyResponse> createCompany(@RequestParam("name") String name,
                                               @RequestParam("description") String description,
                                               @RequestParam("officeAddress") String officeAddress,
                                               @RequestParam("contactEmail") String contactEmail,
                                               @RequestParam("contactPhone") String contactPhone,
                                               @RequestParam("logoFile") MultipartFile logoFile) throws IOException {
        CompanyCreationRequest request = CompanyCreationRequest.builder()
                .name(name)
                .description(description)
                .officeAddress(officeAddress)
                .contactEmail(contactEmail)
                .contactPhone(contactPhone)
                .logoFile(logoFile)
                .build();
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.createCompany(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<CompanyResponse>> getAllCompanies() {
        return ApiResponse.<List<CompanyResponse>>builder()
                .result(companyService.getAllCompanies())
                .build();
    }

    @GetMapping("/{company-id}")
    ApiResponse<CompanyResponse> getCompanyById(@PathVariable("company-id") String id) {
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.getCompanyById(id))
                .build();
    }

    @DeleteMapping("/{company-id}")
    ApiResponse<Void> deleteCompanyById(@PathVariable("company-id") String id) {
        companyService.deleteCompanyById(id);
        return ApiResponse.<Void>builder()
                .message("Company with id " + id + " deleted.")
                .build();
    }
}
