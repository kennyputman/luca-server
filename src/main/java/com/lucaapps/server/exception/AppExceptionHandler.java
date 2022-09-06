package com.lucaapps.server.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler{

    @ResponseBody
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<Object> handleException(AppException e){

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(e.getMessage());
        errors.setStatus(e.getError().getStatus().value());

        return ResponseEntity
                .status(e.getError().getStatus())
                .body(errors);
    }
}