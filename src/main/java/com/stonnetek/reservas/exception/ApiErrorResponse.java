package com.stonnetek.reservas.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private String message;
}
