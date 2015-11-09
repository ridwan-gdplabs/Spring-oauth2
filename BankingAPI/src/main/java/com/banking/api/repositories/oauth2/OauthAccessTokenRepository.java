package com.banking.api.repositories.oauth2;

import org.springframework.data.repository.CrudRepository;

import com.banking.api.models.oauth2.OauthAccessToken;

public interface OauthAccessTokenRepository extends CrudRepository<OauthAccessToken, String> {

}
