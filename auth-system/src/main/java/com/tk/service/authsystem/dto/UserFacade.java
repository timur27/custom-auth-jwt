package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.api.UserNotFoundException;

import java.util.Optional;


public class UserFacade {
    UserRepository userRepository;

    public UserFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDto user) {
        userRepository.save(user);
    }

    public boolean userExists(UserDto user) {
        return userRepository.userExists(user);
    }

    public boolean isUserValid(UserDto user) {
        try {
            Optional<PersistedUser> persistedUser = userRepository.getUserByEmailAndPassword(user);
            if (persistedUser.isPresent()) {
                return true;
            }
        } catch (UserNotFoundException ex) {
            return false;
        }
        return false;
    }

}
