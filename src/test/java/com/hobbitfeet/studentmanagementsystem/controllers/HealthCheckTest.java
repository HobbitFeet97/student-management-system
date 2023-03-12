package com.hobbitfeet.studentmanagementsystem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HealthCheckTest {

    private HealthCheck hcController;

    @BeforeEach
    void setUp() {
        hcController = new HealthCheck();
    }

    @Test
    void healthCheck() {
        //When
        ResponseEntity<String> response = hcController.healthCheck();
        //Then
        HttpStatus expectedStatus = HttpStatus.OK;
        assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }
}