package com.setalite.jobbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyCreationRequest {
    String name;
    String description;
    String contactEmail;
    String contactPhone;
}
