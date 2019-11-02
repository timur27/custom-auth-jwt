package com.tk.service.authsystem.dto;

import com.tk.service.donner.user.UserDto;

import java.util.Optional;

public interface UserRepository {
    void save(UserDto user);
    boolean userExists(UserDto user);
    Optional<PersistedUser> getUserByUsername(String username);
}
