package com.lucaapps.server.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(Error error) {
        super(error.getMessage());
        this.status = error.getStatus();
    }

    public HttpStatus getStatus(){
        return this.status;
    }

}
