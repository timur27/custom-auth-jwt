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
    public boolean userExists(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<PersistedUser> getUserByEmail(String email) throws UserNotFoundException {
        return Optional.of(jpaUserRepository.getByEmail(email)).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<PersistedUser> getUserById(Long id) throws UserNotFoundException{
        return Optional.of(jpaUserRepository.getById(id)).orElseThrow(UserNotFoundException::new);
    }
}
