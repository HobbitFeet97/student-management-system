package com.hobbitfeet.studentmanagementsystem.advice;

import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;
import com.hobbitfeet.studentmanagementsystem.models.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerAdviceTest {

    private ExceptionHandlerAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new ExceptionHandlerAdvice();
    }

    @Test
    void givenHandleException_withNotFoundException_responseHasCode404() {
        //Given
        RuntimeException exception = new EntityNotFoundException("123");
        //When
        ResponseEntity<ErrorResponse> actual = advice.handleException(exception);
        //Then
        HttpStatus expected = HttpStatus.NOT_FOUND;
        assertThat(actual.getStatusCode()).isEqualTo(expected);
        assertThat(actual.getBody().getErrorCode()).isEqualTo(expected.value());
    }

    @Test
    void givenHandleException_withImproperUpdateException_responseHasCode404() {
        //Given
        RuntimeException exception = new ImproperUpdateRequestException("123");
        //When
        ResponseEntity<ErrorResponse> actual = advice.handleException(exception);
        //Then
        HttpStatus expected = HttpStatus.NOT_ACCEPTABLE;
        assertThat(actual.getStatusCode()).isEqualTo(expected);
        assertThat(actual.getBody().getErrorCode()).isEqualTo(expected.value());
    }

    @Test
    void givenHandleException_withUnkownException_responseHasCode500() {
        //Given
        RuntimeException exception = new RuntimeException();
        //When
        ResponseEntity<ErrorResponse> actual = advice.handleException(exception);
        //Then
        HttpStatus expected = HttpStatus.INTERNAL_SERVER_ERROR;
        assertThat(actual.getStatusCode()).isEqualTo(expected);
        assertThat(actual.getBody().getErrorCode()).isEqualTo(expected.value());
    }
}