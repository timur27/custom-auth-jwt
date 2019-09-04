package com.tk.service.authsystem.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJpaUserRepository extends JpaRepository<PersistedUser, Long> {
    boolean existsByEmail(String email);
    Optional<PersistedUser> getByEmail(String email);
    Optional<PersistedUser> getById(Long id);
    Optional<PersistedUser> getByEmailAndPassword(String email, String password);
}
