package com.tk.service.authsystem.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJpaUserRepository extends JpaRepository<PersistedUser, Long> {
    boolean existsByUsername(String username);
    Optional<PersistedUser> getByUsername(String username);
}
