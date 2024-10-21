package com.setalite.jobbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobCreationRequest {
    String title;
    String type;
    String description;
    String location;
    String salary;
    String companyId;
}
