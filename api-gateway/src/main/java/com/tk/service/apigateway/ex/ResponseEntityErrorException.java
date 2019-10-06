package com.tk.service.apigateway.ex;

import org.springframework.http.ResponseEntity;

public class ResponseEntityErrorException extends RuntimeException {
    private ResponseEntity<String> errorResponse;

    public ResponseEntityErrorException(ResponseEntity<String> errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ResponseEntity<String> getErrorResponse() {
        return errorResponse;
    }
}