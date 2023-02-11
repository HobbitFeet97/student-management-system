package com.hobbitfeet.studentmanagementsystem.enums;

public enum ExceptionMessages {
    IMPROPER_UPDATE_REQUEST_GENERIC("We cannot update this entity as it either does not exist, or no unique identifier was provided in the request."),
    IMPROPER_UPDATE_REQUEST_NON_EXISTANT("The entity associated with this id: %s does not exist."),
    INTERNAL_SERVER_ERROR_GENERIC("Internal Server Error.");

    public final String value;

    ExceptionMessages(String value) {
        this.value = value;
    }
}
