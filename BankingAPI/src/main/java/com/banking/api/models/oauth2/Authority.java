package com.banking.api.models.oauth2;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="authorities")
public class Authority {
    
    @Id
    private String authority;
    
    public Authority() { }
    
    public Authority(String authority) {
        this.authority = authority;
    }
    
    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
}
