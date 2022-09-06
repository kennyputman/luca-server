package com.lucaapps.server.exception;

public class AppException extends RuntimeException {

    private final Error error;

    public AppException(Error error) {
        super(error.getMessage());
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
