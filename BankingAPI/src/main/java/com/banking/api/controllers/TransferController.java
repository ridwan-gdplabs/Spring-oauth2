package com.banking.api.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.errors.AccountNotFoundException;
import com.banking.api.errors.InvalidAccountException;
import com.banking.api.errors.InvalidAmountException;
import com.banking.api.errors.LoopbackTransferException;
import com.banking.api.http.requests.TransferPost;
import com.banking.api.models.Account;
import com.banking.api.models.Transfer;
import com.banking.api.models.User;
import com.banking.api.repositories.AccountRepository;
import com.banking.api.repositories.TransferRepository;
import com.banking.api.repositories.UserRepository;
import com.banking.api.services.TransferService;


@RestController
@RequestMapping("accounts/{accountId}/transfers")
public class TransferController {
    
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private AccountRepository accountRepository;
    private TransferRepository transferRepository;
    private UserRepository userRepository;
    private TransferService transferService;
    
    @Autowired
    public TransferController(AccountRepository accountRepository,
                              TransferRepository transferRepository,
                              UserRepository userRepository,
                              TransferService transferService) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
        this.transferService = transferService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Transfer> getTransferHistories(
                                @PathVariable(value="accountId") String accountId) {
        
        Account accountSender = accountRepository.findOne(accountId);
        if (accountSender == null) {
            throw new AccountNotFoundException(accountId);
        }
        
// Check admin or not
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority(ROLE_ADMIN));

        if (isAdmin == true) {
            return transferRepository.findBySender(accountId);
        } else {
            this.checkValidAccount(auth.getName(), accountSender);
            
            return transferRepository.findBySender(accountId);
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    @Transactional
    public Transfer doTransfer(
                            @PathVariable(value="accountId") String sender,
                            @RequestBody TransferPost transferPost) {

        Account accountSender = accountRepository.findByAccountId(sender);
        if (accountSender == null) {
            throw new AccountNotFoundException(sender);
        }
        
        Account accountRecipient = accountRepository.findByAccountId(
                                                            transferPost.getRecipient());
        if (accountRecipient == null) {
            throw new AccountNotFoundException(transferPost.getRecipient());
        }
        
        if (transferPost.getAmount() <= 0) {
            throw new InvalidAmountException();
        }

        if ( sender.equals(transferPost.getRecipient() ) ) {
            throw new LoopbackTransferException();
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority(ROLE_ADMIN));
        
        if (isAdmin == false) {
            this.checkValidAccount(auth.getName(), accountSender);
        }

        return transferService.doTransfer(accountSender, accountRecipient, transferPost.getAmount());
    }

    public void checkValidAccount(String username, Account accountSender) {
        User user = userRepository.findOne(username);
        List<Account> accounts = accountRepository.findByUsers(user);
        
        Boolean isValidAccount = accounts.contains(accountSender);
        if (isValidAccount == false) {
            throw new InvalidAccountException(accountSender.getAccountId());
        }
    }
    
}