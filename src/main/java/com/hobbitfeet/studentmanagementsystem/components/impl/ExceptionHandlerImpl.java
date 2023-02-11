package com.hobbitfeet.studentmanagementsystem.components.impl;

import com.hobbitfeet.studentmanagementsystem.components.api.ExceptionHandlerApi;
import com.hobbitfeet.studentmanagementsystem.enums.ExceptionMessages;
import com.hobbitfeet.studentmanagementsystem.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;

import java.time.LocalDateTime;

@Component
public class ExceptionHandlerImpl implements ExceptionHandlerApi {

    @Override
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return responseBuilder(e);
    }

    private static ResponseEntity<ErrorResponse> responseBuilder(Exception e) {
        if (e.getClass().equals(ImproperUpdateRequestException.class)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    ErrorResponse.builder()
                            .errorCode(HttpStatus.NOT_ACCEPTABLE.value())
                            .errorMessage(e.getMessage())
                            .errorDateTime(LocalDateTime.now())
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder()
                            .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .errorMessage(ExceptionMessages.INTERNAL_SERVER_ERROR_GENERIC.value)
                            .errorDateTime(LocalDateTime.now())
                            .build()
            );
        }
    }
}
