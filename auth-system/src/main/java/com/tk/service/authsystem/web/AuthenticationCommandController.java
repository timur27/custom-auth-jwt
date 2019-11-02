package com.tk.service.authsystem.web;

import com.tk.service.authsystem.security.AuthenticationManager;
import com.tk.service.donner.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationCommandController {
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationCommandController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE
                                                                    , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody UserDto user) {
        return authenticationManager.registerUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE
                                                                 , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginUser(@RequestBody UserDto user) {
        return authenticationManager.loginUser(user);
    }


}