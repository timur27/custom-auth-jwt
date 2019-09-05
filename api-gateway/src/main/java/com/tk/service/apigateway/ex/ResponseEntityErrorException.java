package com.tk.service.apigateway.ex;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseEntityErrorException extends RuntimeException {
    private ResponseEntity<ErrorResponse> errorResponse;
    public ResponseEntityErrorException(ResponseEntity<ErrorResponse> errorResponse) {
        this.errorResponse = errorResponse;
    }
    public ResponseEntity<ErrorResponse> getErrorResponse() {
        return errorResponse;
    }
}