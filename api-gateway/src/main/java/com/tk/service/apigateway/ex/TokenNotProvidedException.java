package com.tk.service.apigateway.ex;

public class TokenNotProvidedException extends RuntimeException {
//    Logger logger = LoggerFactory.getLogger(TokenNotProvidedException.class);

    public TokenNotProvidedException(String message) {
        super(message);
    }
}
