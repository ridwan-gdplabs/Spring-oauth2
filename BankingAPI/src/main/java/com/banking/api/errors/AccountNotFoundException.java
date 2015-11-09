package com.banking.api.errors;

public class AccountNotFoundException extends RuntimeException {

    private String accountId;
    
    public AccountNotFoundException() {
        // TODO Auto-generated constructor stub
    }
    
    public AccountNotFoundException(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
