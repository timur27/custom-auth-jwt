package com.tk.service.authsystem.security;

import com.tk.service.authsystem.dto.PersistedUser;
import com.tk.service.authsystem.dto.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class PasswordMatcher {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserFacade userFacade;


    @Autowired
    public PasswordMatcher(BCryptPasswordEncoder bCryptPasswordEncoder, UserFacade userFacade) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userFacade = userFacade;
    }

    public boolean isPasswordValid(String email, String pass) {
        Optional<PersistedUser> persistedUser = userFacade.getUser(email);
        return persistedUser.filter(user -> bCryptPasswordEncoder.matches(pass, user.getPassword())).isPresent();
    }
}
