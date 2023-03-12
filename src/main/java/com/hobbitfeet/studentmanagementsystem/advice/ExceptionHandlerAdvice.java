package com.hobbitfeet.studentmanagementsystem.advice;

import com.hobbitfeet.studentmanagementsystem.constants.ExceptionConstants;
import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;
import com.hobbitfeet.studentmanagementsystem.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

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
        Optional<Map.Entry<HttpStatus, List<Object>>> status = ExceptionConstants.MAP.entrySet()
                .stream().filter(httpStatusListEntry -> httpStatusListEntry.getValue().contains(e.getClass()))
                .findFirst();
        return status.isPresent() ? status.get().getKey() : null;
    }

    @ExceptionHandler(
            {
                    ImproperUpdateRequestException.class,
                    EntityNotFoundException.class
            }
    )
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return responseBuilder(e);
    }
}
