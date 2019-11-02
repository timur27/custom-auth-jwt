package com.tk.service.donner.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user not found")
public class UserNotFoundException extends UserPrincipalNotFoundException {
    public UserNotFoundException() {
        super("PersistedUser wasn't found");
    }
}