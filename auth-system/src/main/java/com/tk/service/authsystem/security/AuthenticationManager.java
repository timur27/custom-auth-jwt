package com.tk.service.authsystem.security;

import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.dto.UserFacade;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PasswordMatcher passwordMatcher;
    private TokenProvider tokenProvider;
    private UserFacade userFacade;
    private String responseBody;

    @Autowired
    public AuthenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder, PasswordMatcher passwordMatcher, TokenProvider tokenProvider, UserFacade userFacade) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordMatcher = passwordMatcher;
        this.tokenProvider = tokenProvider;
        this.userFacade = userFacade;
    }

    public ResponseEntity registerUser(UserDto user) {
        if (userFacade.userExists(user)) {
            setResponseBody("User with provided data already exist");
        } else {
            persistUser(user);
            setResponseBody("User successfully created");
        }
        return ResponseEntity.ok().body(getResponseBody());
    }

    public ResponseEntity loginUser(UserDto user) {
        if (passwordMatcher.isPasswordValid(user.getEmail(), user.getPassword())) {
            setResponseBody(tokenProvider.generateToken(user.getEmail(), user.getPassword()));
        }
        return ResponseEntity.ok(getResponseBody());
    }

    private String getResponseBody() {
        String tmpResponse = this.responseBody;
        this.responseBody = Strings.EMPTY;
        return tmpResponse;
    }

    private void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    private void persistUser(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFacade.addUser(user);
    }
}
