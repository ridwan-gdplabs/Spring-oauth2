package com.banking.api.models;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;


@Entity(name="Accounts")
public class Account {

    @Id
    @Column(name="account_id")
    private String accountId;

    private double balance;
    
    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name="accounts_users", joinColumns= { @JoinColumn(name="account_id") },
               inverseJoinColumns= { @JoinColumn(name="username") } )
    private List<User>users;

    public Account() {}

    public Account(String accountId, double balance, List<User> users) {
        this.accountId = accountId;
        this.balance   = balance;
        this.users     = users;
    }
    
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
