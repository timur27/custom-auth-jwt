package com.tk.service.apigateway.util.auth;

import com.tk.service.apigateway.api.UserDto;
import com.tk.service.apigateway.application.WorkflowHttpUrls;
import com.tk.service.apigateway.ex.ErrorResponse;
import com.tk.service.apigateway.ex.ResponseEntityErrorException;
import com.tk.service.apigateway.ex.ResponseEntityErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtil {
    private static final String USER_INVALID = "Provided user data is invalid";

    public ResponseEntity performRegisterRequest(UserDto user) {
        return buildAndSendRequest(user, WorkflowHttpUrls.REGISTER);
    }

    public ResponseEntity performLoginUser(UserDto user) {
        return buildAndSendRequest(user, WorkflowHttpUrls.LOGIN);
    }

    private ResponseEntity buildAndSendRequest(UserDto user, String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntityErrorHandler errorHandler = new ResponseEntityErrorHandler();
        errorHandler.setMessageConverters(restTemplate.getMessageConverters());
        restTemplate.setErrorHandler(errorHandler);

        HttpEntity<UserDto> userEntity = new HttpEntity<>(user);
        try {
            return restTemplate.postForEntity(url, userEntity, String.class);
        } catch (ResponseEntityErrorException ex) {
            ResponseEntity<ErrorResponse> errorResponse = ex.getErrorResponse();
            return errorResponse;
        }
    }
}
