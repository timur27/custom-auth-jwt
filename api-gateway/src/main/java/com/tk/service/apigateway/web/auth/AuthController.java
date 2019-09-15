package com.tk.service.apigateway.web.auth;

import com.tk.service.apigateway.api.UserDto;
import com.tk.service.apigateway.util.auth.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController  {
    AuthUtil authUtil;

    @Autowired
    public AuthController(AuthUtil authUtil) {
        this.authUtil = authUtil;
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UserDto user) {
        return authUtil.performLoginUser(user);
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody UserDto user) {
        return authUtil.performRegisterRequest(user);
    }
}