package com.kikking.server.api.auth.exception;

public class PasswordMismatchedException extends RuntimeException {
    public PasswordMismatchedException(String msg) {
        super(msg);
    }
}
