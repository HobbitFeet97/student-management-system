package com.hobbitfeet.studentmanagementsystem.components.api;

import com.hobbitfeet.studentmanagementsystem.models.ErrorResponse;
import org.springframework.http.ResponseEntity;

public interface ExceptionHandlerApi {
    ResponseEntity<ErrorResponse> handleException(Exception e);
}
