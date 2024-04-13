package com.example.exceptions;

public class UnAuthorizedAccessException extends Exception {
    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}
