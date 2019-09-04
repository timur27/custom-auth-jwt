package com.tk.service.authsystem.security


import com.fasterxml.jackson.databind.ObjectMapper
import com.tk.service.authsystem.api.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationManagerTest extends Specification{
    @Autowired MockMvc mockMvc

    def "Should register user, if user not existed before" () {
        given: "User to save"
        UserDto user = produceUser("test_user@gmail.com", "test_password");
        ObjectMapper objectMapper = new ObjectMapper();

        when: "Perform POST request to service"
        MvcResult result = performPostRegister(user, objectMapper)

        then: "User is successfully created"
        result.getResponse().getContentAsString() == "User successfully created"
    }

    def "Should avoid user registering with invalid data"() {
        given: "We have a user in db"
        UserDto user = produceUser("test_user@gmail.com", "test_password")
        ObjectMapper objectMapper = new ObjectMapper()
        performPostRegister(user, objectMapper)

        when: "Try to register another user with the same data"
        MvcResult result = performPostRegister(user, objectMapper)

        then:
        result.getResponse().getContentAsString() == "User with provided data already exist"
    }

    private MvcResult performPostRegister(UserDto user, ObjectMapper objectMapper) {
        return mockMvc.perform(post("http://localhost:8080/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andReturn();
    }

    private UserDto produceUser(String email, String password) {
        return new UserDto(email, password);
    }
}
