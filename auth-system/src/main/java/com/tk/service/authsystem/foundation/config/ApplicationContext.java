package com.tk.service.authsystem.foundation.config;

import com.tk.service.authsystem.dto.*;
import com.tk.service.authsystem.security.PasswordMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationContext {

    @Bean
    UserFacade userFacade(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new UserFacade(userRepository, bCryptPasswordEncoder);
    }

    @Bean
    @Profile("dev")
    public JpaUserRepository jpaUserRepository(SpringJpaUserRepository springJpaUserRepository){
        return new JpaUserRepository(springJpaUserRepository);
    }

    @Bean
    @Profile("test")
    public TestUserRepository testUserRepository(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new TestUserRepository(bCryptPasswordEncoder);
    }

    @Bean
    @Profile({"test", "dev"})
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordMatcher passwordMatcher(BCryptPasswordEncoder bCryptPasswordEncoder, UserFacade userFacade) {
        return new PasswordMatcher(bCryptPasswordEncoder, userFacade);
    }
}
