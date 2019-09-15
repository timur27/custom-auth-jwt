package com.tk.service.authsystem.dto;

import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.api.UserNotFoundException;

import java.util.Optional;


public class JpaUserRepository implements UserRepository {
    SpringJpaUserRepository jpaUserRepository;

    public JpaUserRepository(SpringJpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void save(UserDto user) {
        jpaUserRepository.save(UserWrapper.from(user));
    }

    @Override
    public boolean userExists(UserDto user) {
        return jpaUserRepository.existsByUsername(user.getUsername());
    }

    @Override
    public Optional<PersistedUser> getUserByUsername(String username) {
        return Optional.of(jpaUserRepository.getByUsername(username)).orElse(null);
    }

    @Override
    public Optional<PersistedUser> getUserById(Long id) throws UserNotFoundException{
        return Optional.of(jpaUserRepository.getById(id)).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<PersistedUser> getUserByUsernameAndPassword(UserDto user) {
        return jpaUserRepository.getByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
