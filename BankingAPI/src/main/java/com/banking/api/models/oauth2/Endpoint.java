package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity(name="endpoints")
public class Endpoint {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String method;
    
    @ManyToOne
    @JoinColumn(name = "authority", referencedColumnName = "authority")
    private Authority authority;
    
    private String scope;
    
    @Column(name="ant_pattern")
    private String antPattern;
    
    public Endpoint() { }
    
    public Endpoint(String method, Authority authority, String scope, String antPattern) {
        this.method = method;
        this.authority = authority;
        this.scope = scope;
        this.antPattern = antPattern;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
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
