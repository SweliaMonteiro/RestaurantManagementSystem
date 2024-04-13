package com.test.exceptions;

public class InvalidMenuItemException extends Exception {
    public InvalidMenuItemException(String message) {
        super(message);
    }
}
