package com.tk.service.authsystem.foundation.config;

import com.tk.service.authsystem.dto.JpaUserRepository;
import com.tk.service.authsystem.dto.SpringJpaUserRepository;
import com.tk.service.authsystem.dto.UserFacade;
import com.tk.service.authsystem.dto.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationContext {

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        System.out.println("User facade configured");
        return new UserFacade(userRepository);
    }

    @Bean
    public JpaUserRepository jpaUserRepository(SpringJpaUserRepository springJpaUserRepository){
        return new JpaUserRepository(springJpaUserRepository);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
