package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.api.UserNotFoundException;

import java.util.Optional;

public interface UserRepository {
    void save(UserDto user);
    boolean userExists(String email);
    Optional<PersistedUser> getUserByEmail(String email) throws UserNotFoundException;
    Optional<PersistedUser> getUserById(Long id) throws UserNotFoundException;

}
