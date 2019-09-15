package com.tk.service.apigateway.application.filter;

import com.tk.service.apigateway.ex.TokenNotProvidedException;
import com.tk.service.apigateway.util.jwt.JWTokenHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class ApiGatewayAuthFilter extends HandlerInterceptorAdapter {
    private static final String LOG_IN_PATH = "/login";
    private static final String REGISTER_PATH = "/register";
    private static final String PRIVATE_KEY = "privateKey";
    private JWTokenHelper jwTokenHelper = JWTokenHelper.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        if (req.getRequestURI().equals(LOG_IN_PATH) || req.getRequestURI().equals(REGISTER_PATH)) {
            return true;
        }

        return claimToken(req, res, handler);
    }

    private boolean claimToken(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String privateKeyHeaderValue = Optional.of(req.getHeader(PRIVATE_KEY))
                .orElseThrow(() -> new TokenNotProvidedException("Token was not provided"));
        try {
            jwTokenHelper.claimKey(privateKeyHeaderValue);
        } catch (Exception ex) {
            System.out.println("JWT Token is expired or is incorrect");
            return false;
        }
        return true;
    }
}