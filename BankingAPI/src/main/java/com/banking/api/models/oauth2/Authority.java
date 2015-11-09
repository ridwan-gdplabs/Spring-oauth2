package com.banking.api.models.oauth2;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="authorities")
public class Authority {

    @Id
    private String username;
    
    private String authority;
    
    public Authority() {

    }
    
    public Authority(String username, String authority) {
        this.username  = username;
        this.authority = authority;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
}
