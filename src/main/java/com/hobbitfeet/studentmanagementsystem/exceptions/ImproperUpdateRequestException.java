package com.hobbitfeet.studentmanagementsystem.exceptions;

import com.hobbitfeet.studentmanagementsystem.enums.ExceptionMessages;

public class ImproperUpdateRequestException extends Exception {
    public ImproperUpdateRequestException() {
        super(ExceptionMessages.IMPROPER_UPDATE_REQUEST_GENERIC.value);
    }
    public ImproperUpdateRequestException(String id) {
        super(String.format(ExceptionMessages.IMPROPER_UPDATE_REQUEST_NON_EXISTANT.value, id));
    }
}
