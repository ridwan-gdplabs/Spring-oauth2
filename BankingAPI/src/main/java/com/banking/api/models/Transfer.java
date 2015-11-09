package com.banking.api.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name="Transfers")
public class Transfer {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="transaction_id")
    private int transactionId;
    
    @Column(name="date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    
    private double amount;
    private String sender;
    private String recipient;
    
    public Transfer() {  }

    public Transfer(Date   dateTime,
                    double amount,
                    String sender,
                    String recipient) {
        this.dateTime      = dateTime;
        this.amount        = amount;
        this.sender        = sender;
        this.recipient     = recipient;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return sdf.format(dateTime);
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
