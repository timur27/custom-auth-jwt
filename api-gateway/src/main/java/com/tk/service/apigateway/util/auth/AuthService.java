package com.tk.service.apigateway.util.auth;

import com.tk.service.apigateway.api.UserDto;
import com.tk.service.apigateway.ex.ResponseEntityErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.tk.service.apigateway.application.WorkflowHttpUrls.*;

@Service
public class AuthService {
    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> performRegisterRequest(UserDto user) {
        return performRequest(API + REGISTER, user);
    }

    public ResponseEntity performLoginUser(UserDto user) {
        return performRequest(API + LOGIN, user);
    }

    private ResponseEntity<String> performRequest(String url, UserDto user) {
        try {
            return restTemplate.postForEntity(url, new HttpEntity<>(user), String.class);
        } catch (ResponseEntityErrorException ex) {
            return ex.getErrorResponse();
        }
    }
}
