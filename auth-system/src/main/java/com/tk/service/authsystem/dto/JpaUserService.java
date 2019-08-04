package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserNotFoundException;

import java.util.Optional;

public class JpaUserService implements UserService {

    UserRepository userRepository;

    @Override
    public PersistedUser getUserByEmail(String email) throws UserNotFoundException {
        return Optional.of(userRepository.findByEmail(email)).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<PersistedUser> getUser(Long id) throws UserNotFoundException {
        return Optional.of(userRepository.findById(id)).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void registerUser(PersistedUser persistedUser) {
        userRepository.save(persistedUser);
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
