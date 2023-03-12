package com.hobbitfeet.studentmanagementsystem.exceptions;

public class ImproperUpdateRequestException extends RuntimeException {
    public ImproperUpdateRequestException() {
        super("We cannot update this entity as it either does not exist, or no unique identifier was provided in the request.");
    }
    public ImproperUpdateRequestException(String id) {
        super(String.format("The entity associated with this id: %s does not exist.", id));
    }
}
