package com.tk.service.apigateway.ex;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.List;

public class ResponseEntityErrorHandler implements ResponseErrorHandler {
    private List<HttpMessageConverter<?>> messageConverters;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        ResponseEntity<String> responseEntity = ResponseEntity.noContent().build();
        String errorMsg;
        if (clientHttpResponse.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
            errorMsg = "User with provided data already exist";
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(errorMsg);
        } else if (clientHttpResponse.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()) {
            errorMsg = "Provided user data in invalid";
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }
        throw new ResponseEntityErrorException(responseEntity);
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }
}