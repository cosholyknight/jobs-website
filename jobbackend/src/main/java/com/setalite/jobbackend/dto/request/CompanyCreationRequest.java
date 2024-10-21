package com.setalite.jobbackend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyCreationRequest {
    String name;
    String description;
    String officeAddress;

    String contactEmail;
    String contactPhone;

    MultipartFile logoFile;
}
