package com.tk.service.authsystem.dto;

import com.tk.service.donner.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


public class UserFacade {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserFacade(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addUser(UserDto user) {
        userRepository.save(user);
    }

    public boolean userExists(UserDto user) {
        return userRepository.userExists(user);
    }

    public Optional<PersistedUser> getUser(String username) {
        return userRepository.getUserByUsername(username);
    }

}
