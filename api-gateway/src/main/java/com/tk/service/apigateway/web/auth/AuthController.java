package com.tk.service.apigateway.web.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController  {

    public AuthController() {}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void getTest() {
        System.out.println("Test sout");
        return;
    }

}