package com.hobbitfeet.studentmanagementsystem.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String id) {
        super(String.format("Entity with this id: %s, could not be found.", id));
    }
}
