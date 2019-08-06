package com.tk.service.authsystem.foundation.config;

import com.tk.service.authsystem.dto.JpaUserRepository;
import com.tk.service.authsystem.dto.UserFacade;
import com.tk.service.authsystem.dto.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

    @Bean
    UserRepository userRepository() {
        return new JpaUserRepository();

    }


    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        return new UserFacade(userRepository);
    }
}
