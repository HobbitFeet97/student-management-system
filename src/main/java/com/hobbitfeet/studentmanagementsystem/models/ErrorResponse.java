package com.hobbitfeet.studentmanagementsystem.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
    private LocalDateTime errorDateTime;
}
