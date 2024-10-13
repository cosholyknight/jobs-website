package com.setalite.jobbackend.dto.response;

import com.setalite.jobbackend.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyResponse {
    String id;
    String name;
    String description;
    String contactEmail;
    String contactPhone;
}
