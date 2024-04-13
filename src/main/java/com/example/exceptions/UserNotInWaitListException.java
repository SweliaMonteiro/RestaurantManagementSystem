package com.example.exceptions;

public class UserNotInWaitListException extends Exception {
    public UserNotInWaitListException(String message) {
        super(message);
    }
}
