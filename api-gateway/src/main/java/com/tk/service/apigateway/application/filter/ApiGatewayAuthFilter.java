package com.tk.service.apigateway.application.filter;

import com.tk.service.apigateway.ex.TokenNotProvidedException;
import com.tk.service.apigateway.util.jwt.JWTokenHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class ApiGatewayAuthFilter extends HandlerInterceptorAdapter {
    private static final String LOG_IN_PATH = "/login";
    private static final String REGISTER_PATH = "/register";
    private static final String PRIVATE_KEY = "privateKey";
    private JWTokenHelper jwTokenHelper = JWTokenHelper.getInstance();
//    private Logger logger = LoggerFactory.getLogger(ApiGatewayAuthFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if (req.getRequestURI().equals(LOG_IN_PATH) || req.getRequestURI().equals(REGISTER_PATH)) {
            return true;
        } else {
            claimToken(req, res, handler);
        }
        return true;
    }

    private void claimToken(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String privateKeyHeaderValue = Optional.of(req.getHeader(PRIVATE_KEY))
                .orElseThrow(() -> new TokenNotProvidedException("Token was not provided"));
        try {
            jwTokenHelper.claimKey(privateKeyHeaderValue);
        } catch (Exception ex) {
//            logger.error("JWT Token is expired or is incorrect");
            System.out.println("JWT Token is expired or is incorrect");
        }
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("POST handle");
        return;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception) throws Exception {
        System.out.println("AFTER completion");
        return;
    }
}
