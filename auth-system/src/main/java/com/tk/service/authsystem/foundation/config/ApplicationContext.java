package com.tk.service.authsystem.foundation.config;

import com.tk.service.authsystem.dto.JpaUserService;
import com.tk.service.authsystem.dto.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
    @Bean
    public UserService userService() {
        return new JpaUserService();
    }
}
