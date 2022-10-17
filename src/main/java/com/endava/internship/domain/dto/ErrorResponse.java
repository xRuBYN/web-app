package com.endava.internship.domain.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorResponse {
    private final LocalDateTime timeNow = LocalDateTime.now();
    private final int status;
    private final String message;
    private final String path;
    private final String error;

    public ErrorResponse(int status, String message, String path, String error) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.error = error;
    }
}
