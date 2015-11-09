package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="oauth_client_details")
public class OauthClientDetail {

    @Id
    @Column(name="client_id")
    private String clientId;
    
    @Column(name="resource_ids")
    private String resourceIds;
    
    @Column(name="client_secret")
    private String clientSecret;
    private String scope;
    
    @Column(name="authorized_grant_types")
    private String authorizedGrantTypes;
    
    @Column(name="web_server_redirect_uri")
    private String webServerRedirectUri;
    private String authorities;
    
    @Column(name="access_token_validity")
    private int    accessTokenValidity;
    
    @Column(name="refresh_token_validity")
    private int    refreshTokenValidity;
    
    @Column(name="additional_information")
    private String additionalInformation;
    private String autoapprove;
    
    public OauthClientDetail() {

    }
    
    public OauthClientDetail(String clientId, 
                              String resourceIds,
                              String clientSecret,
                              String scope,
                              String authorizedGrantTypes,
                              String webServerRedirectUri,
                              String authorities,
                              int    accessTokenValidity,
                              int    refreshTokenValidity,
                              String additionalInformation,
                              String autoapprove) {
        this.clientId               = clientId;
        this.resourceIds            = resourceIds;
        this.clientSecret           = clientSecret;
        this.scope                  = scope;
        this.authorizedGrantTypes   = authorizedGrantTypes;
        this.webServerRedirectUri   = webServerRedirectUri;
        this.authorities            = authorities;
        this.accessTokenValidity    = accessTokenValidity;
        this.refreshTokenValidity   = refreshTokenValidity;
        this.additionalInformation  = additionalInformation;
        this.autoapprove            = autoapprove;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }
}
