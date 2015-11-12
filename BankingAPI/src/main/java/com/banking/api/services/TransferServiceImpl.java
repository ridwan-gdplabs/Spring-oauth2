package com.banking.api.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.errors.InsufficientBalanceException;
import com.banking.api.models.Account;
import com.banking.api.models.Transfer;
import com.banking.api.repositories.AccountRepository;
import com.banking.api.repositories.TransferRepository;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransferRepository transferRepository;
    
    @Override
    public Transfer doTransfer(Account sender, Account recipient, double amount) {
        double senderBalance = sender.getBalance();
        
        if (senderBalance < amount) {
            throw new InsufficientBalanceException(sender.getAccountId());
        }
        
        sender.setBalance(sender.getBalance() - amount);
        accountRepository.save(sender);
        
        recipient.setBalance(recipient.getBalance() + amount);
        accountRepository.save(recipient);
        
        Transfer transfer = new Transfer();
        transfer.setDateTime(Calendar.getInstance().getTime());
        transfer.setAmount(amount);
        transfer.setSender(sender.getAccountId());
        transfer.setRecipient(recipient.getAccountId());
        transferRepository.save(transfer);
        
        return transfer;
    }
    
}
