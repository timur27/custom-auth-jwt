package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserNotFoundException;

import java.util.Optional;

public interface UserService {
    public Optional<PersistedUser> getUser(Long id) throws UserNotFoundException;
    public void registerUser(PersistedUser persistedUser);
    public boolean userExists(String email);
    public PersistedUser getUserByEmail(String email) throws UserNotFoundException;
}
