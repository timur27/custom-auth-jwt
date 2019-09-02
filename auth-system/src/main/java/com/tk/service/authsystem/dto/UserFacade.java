package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.api.UserNotFoundException;
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

    public Optional<PersistedUser> getUser(String email) {
        return userRepository.getUserByEmail(email);
    }

}
