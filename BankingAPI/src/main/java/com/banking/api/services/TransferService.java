package com.banking.api.services;

import com.banking.api.models.Account;
import com.banking.api.models.Transfer;

public interface TransferService {
    
    public Transfer doTransfer(Account sender, Account recipient, double amount);

}
