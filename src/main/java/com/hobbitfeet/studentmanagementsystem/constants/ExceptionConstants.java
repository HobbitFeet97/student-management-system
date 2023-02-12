package com.hobbitfeet.studentmanagementsystem.constants;

import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ExceptionConstants {
    public static Map<Class, HttpStatus> MAP = Map.of(
            ImproperUpdateRequestException.class, HttpStatus.NOT_ACCEPTABLE
    );
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong on our side.";
}
