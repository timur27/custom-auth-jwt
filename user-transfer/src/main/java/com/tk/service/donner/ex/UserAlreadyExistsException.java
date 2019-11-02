package com.tk.service.donner.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user already exists")
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("PersistedUser already exists");
    }
}
