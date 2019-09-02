package com.tk.service.authsystem.web;

import com.tk.service.authsystem.api.UserAlreadyExistsException;
import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.dto.UserFacade;
import com.tk.service.authsystem.security.PasswordMatcher;
import com.tk.service.authsystem.security.TokenProvider;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class AuthenticationCommandController {
    UserFacade userFacade;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    TokenProvider tokenProvider;
    PasswordMatcher passwordMatcher;

    @Autowired
    public AuthenticationCommandController(UserFacade userFacade, BCryptPasswordEncoder bCryptPasswordEncoder, TokenProvider tokenProvider, PasswordMatcher passwordMatcher) {
        this.userFacade = userFacade;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
        this.passwordMatcher = passwordMatcher;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUser(@RequestBody UserDto user) throws UserAlreadyExistsException{
        if (userFacade.userExists(user)) {
            throw new UserAlreadyExistsException();
        }
        persistUser(user);
        return ResponseEntity.ok().body(String.format("User with email %s sucessfully created", user.getEmail()));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginUser(@RequestBody UserDto user) {
        String generatedToken = Strings.EMPTY;
        if (passwordMatcher.isPasswordValid(user.getEmail(), user.getPassword())) {
            generatedToken = tokenProvider.generateToken(user.getEmail(), user.getPassword());
        }
        return ResponseEntity.ok(generatedToken);
    }

    private void persistUser(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFacade.addUser(user);
    }

}