package com.tk.service.authsystem.dto;

import com.google.common.collect.Maps;
import com.tk.service.authsystem.api.UserNotFoundException;
import com.tk.service.donner.user.UserDto;
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
        if (!userMap.containsKey(user.getUsername())) {
            userMap.put(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public boolean userExists(UserDto user) {
        return userMap.containsKey(user.getUsername());
    }

    @Override
    public Optional<PersistedUser> getUserByUsername(String username) {
        if (userMap.containsKey(username)) {
            return Optional.of(UserWrapper.from(new UserDto(username, userMap.get(username))));
        }
        return Optional.empty();
    }
}
