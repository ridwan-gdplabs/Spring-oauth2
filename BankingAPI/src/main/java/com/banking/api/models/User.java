package com.banking.api.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

@Entity(name="Users")
public class User {

    @Id
    private String username;
    
    private String name;
    private String password;
    private int enabled;
    
    @ManyToMany(mappedBy="users", fetch=FetchType.LAZY)
    private List<Account> accounts;
    
    public User() {  }

    public User(String username, String password, int enabled, String name) {
    	this.username = username;
    	this.name     = name;
    	this.password = password;
    	this.enabled  = enabled;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
