package com.tk.service.authsystem.security;

import com.tk.service.authsystem.dto.UserFacade;
import com.tk.service.donner.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

    private static final String USR_ALREADY_EXIST_MSG = "User with provided data already exist";
    private static final String USR_SUCCESSFULLY_CREATED_MSG = "User successfully created";
    private static final String USR_INVALID_DATA_PROVIDED = "Provided user data is invalid";

    private UserFacade userFacade;
    private TokenProvider tokenProvider;
    private PasswordMatcher passwordMatcher;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder, PasswordMatcher passwordMatcher, TokenProvider tokenProvider, UserFacade userFacade) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordMatcher = passwordMatcher;
        this.tokenProvider = tokenProvider;
        this.userFacade = userFacade;
    }

    public ResponseEntity registerUser(UserDto user) {
        if (userFacade.userExists(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(USR_ALREADY_EXIST_MSG);
        }
        persistUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(USR_SUCCESSFULLY_CREATED_MSG);
    }

    public ResponseEntity loginUser(UserDto user) {
        if (!passwordMatcher.isPasswordValid(user.getUsername(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(USR_INVALID_DATA_PROVIDED);
        }
        return ResponseEntity.ok(tokenProvider.generateToken(user.getUsername(), user.getPassword()));
    }

    private void persistUser(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFacade.addUser(user);
    }
}
