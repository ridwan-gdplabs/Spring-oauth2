package com.banking.api.models.oauth2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.jdbc.Blob;

@Entity(name="oauth_code")
public class OauthCode {

	@Id
    private String code;
    
    @Lob
    private Blob authentication;
    
    public OauthCode() {

    }
    
    public OauthCode(String code, Blob authentication) {
        this.code = code;
        this.authentication = authentication;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public Blob getAuthentication() {
        return authentication;
    }
    
    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }
    
}
