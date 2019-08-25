//package com.tk.service.authsystem.web;
//
//import com.tk.service.authsystem.api.UserAlreadyExistsException;
//import com.tk.service.authsystem.api.UserDto;
//import com.tk.service.authsystem.dto.UserFacade;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationCommandController {
//    UserFacade userFacade;
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity saveUser(@RequestBody UserDto user) throws UserAlreadyExistsException{
//        if (userFacade.userExists(user)) {
//            throw new UserAlreadyExistsException();
//        }
//        persistUser(user);
//        return ResponseEntity.ok().body(String.format("User with email %s sucessfully created", user.getEmail()));
//    }
//
//    private void persistUser(UserDto user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userFacade.addUser(user);
//    }
//
//}