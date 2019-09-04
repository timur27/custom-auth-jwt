package com.tk.service.authsystem.dto;

import com.google.common.collect.Maps;
import com.tk.service.authsystem.api.UserDto;
import com.tk.service.authsystem.api.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.Optional;

public class TestUserRepository implements UserRepository {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Map<String, String> userMap = Maps.newHashMap();

    @Autowired
    public TestUserRepository(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(UserDto user) {
        if (!userMap.containsKey(user.getEmail())) {
            userMap.put(user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public boolean userExists(UserDto user) {
        return userMap.containsKey(user.getEmail());
    }

    @Override
    public Optional<PersistedUser> getUserByEmail(String email) {
        if (userMap.containsKey(email)) {
            return Optional.of(UserWrapper.from(new UserDto(email, userMap.get(email))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PersistedUser> getUserById(Long id) throws UserNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<PersistedUser> getUserByEmailAndPassword(UserDto user) throws UserNotFoundException {
        return getUserByEmail(user.getEmail());
    }
}
