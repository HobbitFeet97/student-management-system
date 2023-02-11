package com.hobbitfeet.studentmanagementsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthCheck {
    @GetMapping("/health-check")
    public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Healthcheck OK.");
    }
}
