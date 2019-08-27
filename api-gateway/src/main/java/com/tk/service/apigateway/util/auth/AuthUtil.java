package com.tk.service.apigateway.util.auth;

import com.tk.service.authsystem.api.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthUtil {
    public ResponseEntity performRegisterRequest(UserDto user) {
        RestTemplate restTemplate = new RestTemplate();
        String registerUrl = "http://localhost:8080/register";
        HttpEntity<UserDto> userEntity = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate.postForEntity(registerUrl, userEntity, String.class);
        return response;
    }

    public String performLoginUser(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String registerUrl = "http://localhost:8080/login";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> params = new HashMap<String, String>();
        params.put("usrename", username);
        params.put("password", password);

        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<String> response = restTemplate.exchange(registerUrl, HttpMethod.POST, entity, String.class, params);
        return response.getBody();
    }
}
