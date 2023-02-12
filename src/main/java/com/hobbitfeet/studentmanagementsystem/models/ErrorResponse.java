package com.hobbitfeet.studentmanagementsystem.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
    private String errorDateTime;
}
