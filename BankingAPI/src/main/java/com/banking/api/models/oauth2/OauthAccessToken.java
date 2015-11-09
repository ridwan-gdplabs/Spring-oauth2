package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.jdbc.Blob;

@Entity(name="oauth_access_token")
public class OauthAccessToken {

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
    
    @Lob
    private Blob   authentication;
    
    @Column(name="refresh_token")
    private String refreshToken;
    
    public OauthAccessToken() {
        // TODO Auto-generated constructor stub
    }
    
    public OauthAccessToken  (String tokenId, 
                              Blob   token, 
                              String authenticationId,
                              String username,
                              String clientId,
                              Blob   authentication,
                              String refreshToken) {
        this.tokenId 		   = tokenId;
        this.token      	   = token;
        this.authenticationId  = authenticationId;
        this.username		   = username;
        this.clientId		   = clientId;
        this.authentication	   = authentication;
        this.refreshToken	   = refreshToken;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setToken_id(String tokenId) {
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
    
    public void setClient_id(String clientId) {
        this.clientId = clientId;
    }
    
    public Blob getAuthentication() {
        return authentication;
    }
    
    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }
    
    public String getRefreshToken() {
        return refreshToken;
    }
    
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
}
