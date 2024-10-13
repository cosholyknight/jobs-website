package com.setalite.jobbackend.mapper;

import com.setalite.jobbackend.dto.request.CompanyCreationRequest;
import com.setalite.jobbackend.dto.response.CompanyResponse;
import com.setalite.jobbackend.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toCompany(CompanyCreationRequest request);

    CompanyResponse toCompanyResponse(Company company);
}
