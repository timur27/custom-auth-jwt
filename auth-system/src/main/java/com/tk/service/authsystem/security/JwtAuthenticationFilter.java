package com.tk.service.authsystem.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tk.service.authsystem.api.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tk.service.authsystem.security.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UserDto user = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req,
                                         HttpServletResponse res,
                                         FilterChain chain,
                                         Authentication auth) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((UserDto) auth.getPrincipal()).getEmail())
                .withSubject(((UserDto) auth.getPrincipal()).getPassword())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
