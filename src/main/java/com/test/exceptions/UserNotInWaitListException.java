package com.test.exceptions;

public class UserNotInWaitListException extends Exception {
    public UserNotInWaitListException(String message) {
        super(message);
    }
}
