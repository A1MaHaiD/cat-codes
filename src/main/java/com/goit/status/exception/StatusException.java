package com.goit.status.exception;
public abstract class StatusException extends RuntimeException {
    public StatusException() {
    }

    public StatusException(String message) {
        super(message);
    }

    public StatusException(String message, Throwable cause) {
        super(message, cause);
    }
    public abstract int getHttpCode();
}