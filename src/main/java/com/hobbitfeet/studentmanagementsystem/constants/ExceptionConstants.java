package com.hobbitfeet.studentmanagementsystem.constants;

import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class ExceptionConstants {
    public static final Map<HttpStatus, List<Object>> MAP = Map.of(
            HttpStatus.NOT_ACCEPTABLE, List.of(ImproperUpdateRequestException.class),
            HttpStatus.NOT_FOUND, List.of(
                    EntityNotFoundException.class
            )
    );
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong on our side.";

    private ExceptionConstants() {}
}
