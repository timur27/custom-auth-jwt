package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.api.UserNotFoundException;

import java.util.Optional;

public interface UserRepository {
    void save(UserDto user);
    boolean userExists(UserDto user);
    Optional<PersistedUser> getUserByUsername(String username);
    Optional<PersistedUser> getUserById(Long id) throws UserNotFoundException;
    Optional<PersistedUser> getUserByUsernameAndPassword(UserDto user) throws UserNotFoundException;

}
