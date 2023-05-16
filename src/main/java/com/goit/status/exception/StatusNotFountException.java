package com.goit.status.exception;

import lombok.Getter;

public class StatusNotFountException extends StatusInternalErrorException {
    @Getter
    private final int httpCode = 404;

    public StatusNotFountException(String message) {
        super(message);
    }

    public StatusNotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}