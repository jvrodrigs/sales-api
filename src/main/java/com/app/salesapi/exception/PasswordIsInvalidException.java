package com.app.salesapi.exception;

public class PasswordIsInvalidException extends RuntimeException {
    public PasswordIsInvalidException() {
        super("Password is invalid.");
    }
}
