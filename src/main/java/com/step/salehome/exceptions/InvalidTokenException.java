package com.step.salehome.exceptions;

public class InvalidTokenException extends Exception {
    private String message;

    public InvalidTokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
