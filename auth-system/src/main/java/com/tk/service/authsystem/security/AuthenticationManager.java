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
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PasswordMatcher passwordMatcher;
    private TokenProvider tokenProvider;
    private UserFacade userFacade;

    @Autowired
    public AuthenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder, PasswordMatcher passwordMatcher, TokenProvider tokenProvider, UserFacade userFacade) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordMatcher = passwordMatcher;
        this.tokenProvider = tokenProvider;
        this.userFacade = userFacade;
    }

    public ResponseEntity<String> registerUser(UserDto user) {
        if (userFacade.userExists(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        persistUser(user);
        return ResponseEntity.ok("User successfully created");
    }

    public ResponseEntity<String> loginUser(UserDto user) {
        if (!passwordMatcher.isPasswordValid(user.getUsername(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(tokenProvider.generateToken(user.getUsername(), user.getPassword()));
    }

    private void persistUser(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFacade.addUser(user);
    }
}
