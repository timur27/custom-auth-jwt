package com.tk.service.apigateway.util.auth;

import com.tk.service.apigateway.ex.ResponseEntityErrorException;
import com.tk.service.donner.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.tk.service.apigateway.application.WorkflowHttpUrls.*;

@Service
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    private static final String REQUEST_ERROR_MSG = "Exception while performing request to Authentication service";

    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> performRegisterRequest(UserDto user) {
        return performRequest(API + REGISTER, user);
    }

    public ResponseEntity performLoginRequest(UserDto user) {
        return performRequest(API + LOGIN, user);
    }

    private ResponseEntity<String> performRequest(String url, UserDto user) {
        try {
            return restTemplate.postForEntity(url, new HttpEntity<>(user), String.class);
        } catch (ResponseEntityErrorException ex) {
            LOGGER.error(REQUEST_ERROR_MSG, ex);
            throw new ResponseEntityErrorException(ex.getErrorResponse());
        }
    }
}