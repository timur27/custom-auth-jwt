package com.tk.service.apigateway.util.auth;

import com.tk.service.apigateway.dto.UserDto;
import com.tk.service.apigateway.ex.ApiResponse;
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

    public ResponseEntity<ApiResponse> performRegisterRequest(UserDto user) {
        return performRequest(API + REGISTER, user);
    }

    public ResponseEntity<ApiResponse> performLoginRequest(UserDto user) {
        return performRequest(API + LOGIN, user);
    }

    private ResponseEntity<ApiResponse> performRequest(String url, UserDto user) {
        ResponseEntity responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(user), String.class);
        return new ResponseEntity<>(new ApiResponse(responseEntity.getStatusCode().value(), responseEntity.getBody().toString()), responseEntity.getStatusCode());
    }
}