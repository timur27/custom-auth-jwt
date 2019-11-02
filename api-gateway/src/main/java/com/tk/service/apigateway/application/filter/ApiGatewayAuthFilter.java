package com.tk.service.apigateway.application.filter;

import com.tk.service.apigateway.ex.InvalidTokenException;
import com.tk.service.apigateway.ex.TokenNotProvidedException;
import com.tk.service.apigateway.util.jwt.JWTokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.tk.service.apigateway.application.WorkflowHttpUrls.*;

@Service
public class ApiGatewayAuthFilter extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayAuthFilter.class);
    private static final String PRIVATE_KEY = "privateKey";
    private static final String INVALID_TOKEN_MESSAGE = "JWT Token is expired or is incorrect";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        if (getEndpointsWithNoTokenNeeded().contains(req.getRequestURI())) {
            return true;
        } else {
            return claimToken(req.getHeader(PRIVATE_KEY));
        }
    }

    private boolean claimToken(String privateKey) {
        String privateKeyHeaderValue = Optional.of(privateKey)
                .orElseThrow(() -> new TokenNotProvidedException("Token was not provided"));
        try {
            JWTokenHelper.claimKey(privateKeyHeaderValue);
        } catch (Exception ex) {
            LOGGER.error(INVALID_TOKEN_MESSAGE);
            throw new InvalidTokenException(INVALID_TOKEN_MESSAGE, ex);
        }
        return true;
    }
}