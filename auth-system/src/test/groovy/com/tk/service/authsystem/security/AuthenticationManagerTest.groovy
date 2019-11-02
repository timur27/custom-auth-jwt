package com.tk.service.authsystem.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.tk.service.donner.user.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationManagerTest extends Specification{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired MockMvc mockMvc

    public static final String API = "http://localhost:8080"
    public static final String REGISTER = API + "/register"
    public static final String LOGIN = API + "/login"

    def "Should register user, if user not existed before" () {
        given: "User to save"
        UserDto user = produceUser("test_user@gmail.com", "test_password");
        ObjectMapper objectMapper = new ObjectMapper()

        when: "Perform POST request to service"
        MvcResult result = performPostRequest(REGISTER, objectMapper.writeValueAsString(user))

        then: "User is successfully created"
        result.getResponse().getContentAsString() == "User successfully created"
    }

    def "Should avoid user registering with invalid data"() {
        given: "We have a user in db"
        UserDto user = produceUser("test_user@gmail.com", "test_password")
        ObjectMapper objectMapper = new ObjectMapper()
        performPostRequest(REGISTER, objectMapper.writeValueAsString(user))

        when: "Try to register another user with the same data"
        MvcResult result = performPostRequest(REGISTER, objectMapper.writeValueAsString(user))

        then:
        result.getResponse().getStatus() == HttpStatus.CONFLICT.value()
    }

    def "Should correctly log in registered user and return JWT in response" () {
        given: "Registered user in db"
        UserDto user = produceUser("test_user@gmail.com", "test_password")
        ObjectMapper objectMapper = new ObjectMapper()
        performPostRequest(REGISTER, objectMapper.writeValueAsString(user))

        when: "Try to log in user by providing correct data"
        MvcResult result = performPostRequest(LOGIN, objectMapper.writeValueAsString(user))

        then: "User is successfully authenticated and JWT is returned"
        result.getResponse().getStatus() == HttpStatus.OK.value()
        result.getResponse().getContentAsString().split("\\.").length == 3
    }

    def "Should return error message when trying to log in with invalid user data"() {
        when: "Try to log in with invalid user data"
        UserDto user = produceUser("test_useraa@gmail.com", "test_passwordds")
        ObjectMapper objectMapper = new ObjectMapper()
        MvcResult result = performPostRequest(LOGIN, objectMapper.writeValueAsString(user))

        then: "There is a response with invalid data msg"
        result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()
    }

    private MvcResult performPostRequest(String url, String userString) {
        return mockMvc.perform(post(url)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(userString))
                      .andReturn()
    }

    private static UserDto produceUser(String username, String password) {
        return new UserDto(username, password)
    }
}
