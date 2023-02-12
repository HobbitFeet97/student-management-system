package com.hobbitfeet.studentmanagementsystem.components.impl;

import com.hobbitfeet.studentmanagementsystem.components.api.ExceptionHandlerApi;
import com.hobbitfeet.studentmanagementsystem.constants.ExceptionConstants;
import com.hobbitfeet.studentmanagementsystem.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ExceptionHandlerImpl implements ExceptionHandlerApi {

    private static ResponseEntity<ErrorResponse> responseBuilder(Exception e) {
        ErrorResponse error = exceptionBuilder(e);
        return ResponseEntity.status(error.getErrorCode()).body(error);
    }

    private static ErrorResponse exceptionBuilder(Exception e) {
        HttpStatus mappedStatus = retrieveStatus(e);
        if (mappedStatus != null) {
            return ErrorResponse.builder()
                    .errorCode(mappedStatus.value())
                    .errorMessage(e.getMessage())
                    .errorDateTime(LocalDateTime.now().toString())
                    .build();
        }
        return defaultResponse();
    }

    private static ErrorResponse defaultResponse() {
        return ErrorResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(ExceptionConstants.INTERNAL_SERVER_ERROR_MESSAGE)
                .errorDateTime(LocalDateTime.now().toString())
                .build();
    }

    private static HttpStatus retrieveStatus(Exception e) {
        Optional<Map.Entry<HttpStatus, List<Class>>> status = ExceptionConstants.MAP.entrySet()
                .stream().filter(httpStatusListEntry -> httpStatusListEntry.getValue().contains(e.getClass()))
                .findFirst();
        return status.isPresent() ? status.get().getKey() : null;
    }

    @Override
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return responseBuilder(e);
    }
}
