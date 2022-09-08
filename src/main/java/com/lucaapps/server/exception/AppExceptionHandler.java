package com.lucaapps.server.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<Object> handleException(AppException e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .status(e.getStatus().value())
                .build();

        return ResponseEntity
                .status(e.getStatus())
                .body(errors);
    }
}
