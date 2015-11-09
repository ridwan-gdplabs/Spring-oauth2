package com.banking.api.errors;

public class InvalidAccountException extends RuntimeException {

    private String accountId;
    
    public InvalidAccountException() {}
    
    public InvalidAccountException(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
