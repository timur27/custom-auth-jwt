package com.tk.service.authsystem.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user not found")
public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException() {
        super("PersistedUser wasn't found");
    }
}
