package com.kikking.server.api.user.exception;

public class IdDuplicationException extends RuntimeException {
    public IdDuplicationException(String msg) {
        super(msg);
    }
}
