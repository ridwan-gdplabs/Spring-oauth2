package com.banking.api.errors;

public class InsufficientBalanceException extends RuntimeException {

    private String accountId;
    
    public InsufficientBalanceException() {
        // TODO Auto-generated constructor stub
    }
    
    public InsufficientBalanceException(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
