package com.tk.service.authsystem.security;

import com.tk.service.authsystem.api.UserNotFoundException;
import com.tk.service.authsystem.dto.JpaUserRepository;
import com.tk.service.authsystem.dto.PersistedUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {
    JpaUserRepository userRepository;

    public UserDetailsServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PersistedUser user = userRepository.getUserByEmail(email)
                                           .orElseThrow(UserNotFoundException::new);

        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
