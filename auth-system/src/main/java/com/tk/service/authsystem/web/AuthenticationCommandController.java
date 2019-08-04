package com.tk.service.authsystem.web;

import com.tk.service.authsystem.api.UserAlreadyExistsException;
import com.tk.service.authsystem.dto.PersistedUser;
import com.tk.service.authsystem.dto.UserService;
import org.apache.catalina.User;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationCommandController {
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public void registerNewUser(@PathVariable("id") Long id) throws UserAlreadyExistsException {
        if (userService.userExists(new Long("2").toString())) {
            throw new UserAlreadyExistsException();
        }

        userService.registerUser(new PersistedUser("d", "asd"));
    }

    @RequestMapping(value = "/register/{email}/{password}", method = RequestMethod.POST)
    public void addUser(@PathVariable("email") String email, @PathVariable("password") String password) throws UserAlreadyExistsException {//REST Endpoint.
        if (userService.userExists(email)) {
            throw new UserAlreadyExistsException();
        }

        userService.registerUser(new PersistedUser(email, password));
        }
}
