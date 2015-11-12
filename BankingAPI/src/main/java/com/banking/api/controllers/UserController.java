package com.banking.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.models.User;
import com.banking.api.repositories.UserRepository;


@RestController
@RequestMapping("/me")
public class UserController {

    private UserRepository userRepository;
    
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public User getUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findOne(auth.getName());
    }
}
