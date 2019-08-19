package com.tk.service.apigateway.web.auth;

import com.tk.service.authsystem.api.UserAlreadyExistsException;
import com.tk.service.authsystem.util.SignUpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController  {
    SignUpUtil signUpUtil;


    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            return signUpUtil.saveUser(email, password);
        } catch (UserAlreadyExistsException ex) {
            // TODO Add LOGGER
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
