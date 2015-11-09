package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.jdbc.Blob;

@Entity(name="oauth_client_token")
public class OauthClientToken {

    @Id
    @Column(name="token_id")
    private String tokenId;
    
    @Lob
    private Blob   token;
    
    @Column(name="authentication_id")
    private String authenticationId;
    
    @Column(name="user_name")
    private String username;
    
    @Column(name="client_id")
    private String clientId;
    
    public OauthClientToken() {

    }
    
    public OauthClientToken(String tokenId, 
                            Blob   token, 
                            String authenticationId,
                            String username,
                            String clientId) {
        this.tokenId          = tokenId;
        this.token            = token;
        this.authenticationId = authenticationId;
        this.username         = username;
        this.clientId         = clientId;
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
    
    public String getAuthenticationId() {
        return authenticationId;
    }
    
    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
