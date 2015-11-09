package com.banking.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error getAccountNotFoundException (AccountNotFoundException e) {
        String accountId = e.getAccountId();
        return new Error(4, "Account " + accountId + " is not found");
    }
    
    @ExceptionHandler(InvalidAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error getInvalidAmountException (InvalidAmountException e) {
        return new Error(1, "Balance must be greater than zero");
    }
    
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error getInsufficientBalanceException (InsufficientBalanceException e) {
        String accountId = e.getAccountId();
        return new Error(2, "Insufficient balance account " + accountId);
    }
    
    @ExceptionHandler(InvalidAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error getInvalidAccountException (InvalidAccountException e) {
        String accountId = e.getAccountId();
        return new Error(3, "Invalid account " + accountId);
    }
    
    @ExceptionHandler(LoopbackTransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error getLoopbackTransferException (LoopbackTransferException e) {
        return new Error(5, "Loopback transfer");
    }
}
