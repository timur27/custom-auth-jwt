package com.tk.service.apigateway.ex;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ResponseEntityErrorHandler implements ResponseErrorHandler {
    private List<HttpMessageConverter<?>> messageConverters;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        String errorMsg = Strings.EMPTY;
        if (clientHttpResponse.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
            errorMsg = "User with provided data already exist";
        } else if (clientHttpResponse.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()) {
            errorMsg = "Provided user data in invalid";
        }
        ResponseEntity<ErrorResponse> responseEntity = ResponseEntity.of(Optional.of(new ErrorResponse(errorMsg, clientHttpResponse.getRawStatusCode())));
        throw new ResponseEntityErrorException(responseEntity);
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }
}
