//package com.workintech.eCommerceBackend.controller;
//
//import com.workintech.eCommerceBackend.dto.UserDto;
//import com.workintech.eCommerceBackend.entity.User;
//import com.workintech.eCommerceBackend.service.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/v1/auth")
//@CrossOrigin("*")
//public class AuthController {
//
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    public AuthController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @PostMapping("/")
//    public User register(@RequestBody UserDto userDto) {
//        User newUser = new User();
//        newUser.setFullName(userDto.getFullName());
//        newUser.setEmail(userDto.getEmail());
//        newUser.setPassword(userDto.getPassword());
//
//        return authenticationService.register(newUser);
//    }
//}
