package com.tk.service.authsystem.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Exception while credentials parsing")
public class CredentialsValidationException extends Exception {
    public CredentialsValidationException() {
        super();
    }
}
