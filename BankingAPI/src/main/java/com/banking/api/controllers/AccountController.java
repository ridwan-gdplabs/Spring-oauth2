package com.banking.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.models.Account;
import com.banking.api.models.User;
import com.banking.api.repositories.AccountRepository;
import com.banking.api.repositories.UserRepository;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final String ROLE_ADMIN = "ROLE_admin";
    
    private AccountRepository accountRepository;
    private UserRepository    userRepository;
    
    @Autowired
    public AccountController(AccountRepository accountRepository, 
                             UserRepository    userRepository) {
        this.accountRepository       = accountRepository;
        this.userRepository          = userRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Account> getAccounts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority(ROLE_ADMIN));

        if (isAdmin == true) {
            return accountRepository.findAll();
        } else {
            User user = userRepository.findOne(auth.getName());
            return accountRepository.findByUsers(user);
        }
    }
}
