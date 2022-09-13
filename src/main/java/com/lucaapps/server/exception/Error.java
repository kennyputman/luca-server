package com.lucaapps.server.exception;

import org.springframework.http.HttpStatus;

public enum Error {

    INVOICE_NOT_FOUND("Invoice not found", HttpStatus.NOT_FOUND),
    USERNAME_NOT_FOUND("username not found", HttpStatus.NOT_FOUND),
    DUPLICATE_USER("User email is already taken", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;


    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
