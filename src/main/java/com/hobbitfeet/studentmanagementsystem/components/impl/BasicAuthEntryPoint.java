package com.hobbitfeet.studentmanagementsystem.components.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbitfeet.studentmanagementsystem.components.api.ExceptionHandlerApi;
import com.hobbitfeet.studentmanagementsystem.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class BasicAuthEntryPoint implements AuthenticationEntryPoint {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ExceptionHandlerApi exceptionApi;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<ErrorResponse> entity = exceptionApi.handleException(authException);
        response.setStatus(entity.getStatusCode().value());
        String json = mapper.writeValueAsString(entity.getBody() != null ? entity.getBody() : Map.of());
        response.getWriter().write(json);
        response.flushBuffer();
    }
}
