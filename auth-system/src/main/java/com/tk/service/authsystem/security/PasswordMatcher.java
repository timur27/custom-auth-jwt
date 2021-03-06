package com.tk.service.authsystem.security;

import com.tk.service.authsystem.dto.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordMatcher {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserFacade userFacade;

    @Autowired
    public PasswordMatcher(BCryptPasswordEncoder bCryptPasswordEncoder, UserFacade userFacade) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userFacade = userFacade;
    }

    public boolean isPasswordValid(String username, String pass) {
        return userFacade.getUser(username).filter(user -> bCryptPasswordEncoder.matches(pass, user.getPassword())).isPresent();
    }
}
