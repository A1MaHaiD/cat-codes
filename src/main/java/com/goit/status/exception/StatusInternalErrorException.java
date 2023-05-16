package com.goit.status.exception;
public class StatusInternalErrorException extends StatusException {
    private final int httpCode = 500;

    public StatusInternalErrorException(String message) {
        super(message);
    }

    public StatusInternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }
}
