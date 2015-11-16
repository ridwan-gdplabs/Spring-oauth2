package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="authorities")
public class Authority {
    
    @Id
    private String authority;
    
    private String method;
    
    private String scope;
    
    @Column(name="ant_pattern")
    private String antPattern;
    
    public Authority() { }
    
    public Authority(String authority, String method, String scope, String antPattern) {
        this.authority = authority;
        this.method = method;
        this.scope = scope;
        this.antPattern = antPattern;
    }
    
    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAntPattern() {
        return antPattern;
    }

    public void setAntPattern(String antPattern) {
        this.antPattern = antPattern;
    }
}
