package com.tk.service.authsystem.web;

import com.tk.service.authsystem.api.UserAlreadyExistsException;
import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.dto.PersistedUser;
import com.tk.service.authsystem.dto.UserFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationCommandController {
    UserFacade userFacade;

    public AuthenticationCommandController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/register/{email}/{password}", method = RequestMethod.POST)
    public void addUser(@PathVariable("email") String email, @PathVariable("password") String password) throws UserAlreadyExistsException {//REST Endpoint.
        if (userFacade.userExists(email)) {
            throw new UserAlreadyExistsException();
        }

        userFacade.addUser(new UserDto(email, password));
    }
}
