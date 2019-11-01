package com.tk.service.apigateway.ex;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
