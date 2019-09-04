package com.tk.service.apigateway.util.auth;

import com.tk.service.apigateway.application.WorkflowHttpUrls;
import com.tk.service.authsystem.api.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtil {
    private static final String USER_INVALID = "Provided user data is invalid";

    public ResponseEntity<String> performRegisterRequest(UserDto user) {
        return buildAndSendRequest(user, WorkflowHttpUrls.REGISTER);
    }

    public ResponseEntity<String> performLoginUser(UserDto user) {
        return translateLoginResponse(buildAndSendRequest(user, WorkflowHttpUrls.LOGIN));
    }

    private ResponseEntity<String> translateLoginResponse(ResponseEntity<String> res) {
        return res.getHeaders().getContentLength() == 0L ? ResponseEntity.ok(USER_INVALID)
                                                         : ResponseEntity.ok(String.format("access_token: %s", res.getBody()));
    }

    private ResponseEntity<String> buildAndSendRequest(UserDto user, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserDto> userEntity = new HttpEntity<>(user);
        return restTemplate.postForEntity(url, userEntity, String.class);
    }
}
