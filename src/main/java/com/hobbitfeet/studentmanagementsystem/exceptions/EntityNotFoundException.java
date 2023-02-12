package com.hobbitfeet.studentmanagementsystem.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        super("This entity does not exist or has been deleted.");
    }

    public EntityNotFoundException(String id) {
        super(String.format("Entity with this id: %s, could not be found.", id));
    }
}
