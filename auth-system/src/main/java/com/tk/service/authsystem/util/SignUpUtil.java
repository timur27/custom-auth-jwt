package com.tk.service.authsystem.util;

import com.tk.service.authsystem.api.UserAlreadyExistsException;
import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.dto.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUpUtil {
    UserFacade userFacade;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpUtil(UserFacade userFacade, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userFacade = userFacade;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity saveUser(String email, String password) throws UserAlreadyExistsException {
        UserDto user = new UserDto(email, password);

        if (userFacade.userExists(user)) {
            throw new UserAlreadyExistsException();
        }
        persistUser(user);
        return ResponseEntity.ok().body(String.format("User with email %s sucessfully created", user.getEmail()));
    }

    private void persistUser(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFacade.addUser(user);
    }
}
