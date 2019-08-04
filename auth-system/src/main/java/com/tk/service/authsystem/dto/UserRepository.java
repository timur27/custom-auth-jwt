package com.tk.service.authsystem.dto;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<PersistedUser, Long> {
    public PersistedUser findByEmail(String email);
    public boolean existsByEmail(String email);
}
