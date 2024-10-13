package com.setalite.jobbackend.exception;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class AppException extends RuntimeException{
    private final ErrorCode errorCode;
}
