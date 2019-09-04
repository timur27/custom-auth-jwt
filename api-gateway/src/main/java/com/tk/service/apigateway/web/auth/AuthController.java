package com.tk.service.apigateway.web.auth;

import com.tk.service.apigateway.api.UserDto;
import com.tk.service.apigateway.util.auth.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController  {
    AuthUtil authUtil;

    @Autowired
    public AuthController(AuthUtil authUtil) {
        this.authUtil = authUtil;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        return authUtil.performLoginUser(new UserDto(username, password));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        return authUtil.performRegisterRequest(new UserDto(username, password));
    }
}