package com.tk.service.apigateway.ex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handle(final HttpClientErrorException ex) {
        return new ResponseEntity<>(new ApiResponse(ex.getStatusCode().value(), ex.getResponseBodyAsString()), ex.getStatusCode());
    }
}
