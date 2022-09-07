package com.lucaapps.server.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;

}
