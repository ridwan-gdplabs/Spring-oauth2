package com.banking.api.http.requests;


public class TransferPost {

    private double amount;
    
    private String recipient;

    public TransferPost() {
        
    }
    
    public TransferPost(double amount, String recipient) {
        this.amount    = amount;
        this.recipient = recipient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
