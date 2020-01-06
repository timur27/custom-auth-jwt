package com.tk.service.authsystem.dto;

import java.util.Optional;


public class JpaUserRepository implements UserRepository {
    private final SpringJpaUserRepository jpaUserRepository;

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
}
