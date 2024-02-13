package com.kikking.server.api.user.exception;

public class PasswordConfirmMismatchException extends RuntimeException {
    public PasswordConfirmMismatchException(String msg) {
        super(msg);
    }
}
