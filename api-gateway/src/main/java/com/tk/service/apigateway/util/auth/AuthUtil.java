package com.tk.service.apigateway.util.auth;

import com.tk.service.authsystem.api.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    public String performLoginUser(UserDto user) {
        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = "http://localhost:8080/login";
        HttpEntity<UserDto> userEntity = new HttpEntity<>(user);
        return translateLoginResponse(restTemplate.exchange(loginUrl, HttpMethod.POST, userEntity, String.class));
    }


    private String translateLoginResponse(ResponseEntity<String> res) {
        return res.getHeaders().getContentLength() == 0L ? String.format("Provided user data is invalid") : res.getBody();
    }


}
