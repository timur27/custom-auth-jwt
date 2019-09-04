package com.tk.service.apigateway.ex;

public class TokenNotProvidedException extends RuntimeException {
    public TokenNotProvidedException(String message) {
        super(message);
    }
}
