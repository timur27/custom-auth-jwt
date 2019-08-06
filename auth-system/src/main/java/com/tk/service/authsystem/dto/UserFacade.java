package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;


public class UserFacade {
    UserRepository userRepository;

    public UserFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDto user) {
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.userExists(email);
    }
}
