package com.setalite.jobbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    JOB_NOT_EXISTED(1001, "Job is not existed.", HttpStatus.BAD_REQUEST),
    COMPANY_NOT_EXISTED(1002, "Company is not existed.", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
