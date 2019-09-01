package com.tk.service.apigateway.util.auth;

import com.tk.service.authsystem.api.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtil {
    public ResponseEntity performRegisterRequest(UserDto user) {
        RestTemplate restTemplate = new RestTemplate();
        String registerUrl = "http://localhost:8080/register";
        HttpEntity<UserDto> userEntity = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate.postForEntity(registerUrl, userEntity, String.class);
        return response;
    }

    public ResponseEntity performLoginUser(UserDto user) {
        RestTemplate restTemplate = new RestTemplate();
        String loginUrl = "http://localhost:8080/login";
        HttpEntity<UserDto> userEntity = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, userEntity, String.class);
        return response;
    }
}
