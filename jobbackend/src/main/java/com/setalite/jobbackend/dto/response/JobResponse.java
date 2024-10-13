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
public class JobResponse {
    String id;
    String title;
    String type;
    String description;
    String location;
    String salary;
    Company company;
}
