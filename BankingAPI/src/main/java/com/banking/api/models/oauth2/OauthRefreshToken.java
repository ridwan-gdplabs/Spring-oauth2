package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.jdbc.Blob;

@Entity(name="oauth_refresh_token")
public class OauthRefreshToken {

    @Id
    @Column(name="token_id")
    private String tokenId;

    @Lob
    private Blob token;
    
    @Lob
    private Blob authentication;
    
    public OauthRefreshToken() {

    }
    
    public OauthRefreshToken(String tokenId, Blob token, Blob authentication) {
        this.tokenId 		= tokenId;
        this.token    		= token;
        this.authentication = authentication;
    }

    public String getTokenId() {
        return tokenId;
    }
    
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
    
    public Blob getToken() {
        return token;
    }
    
    public void setToken(Blob token) {
        this.token = token;
    }
    
    public Blob getAuthentication() {
        return authentication;
    }
    
    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }
    
}
