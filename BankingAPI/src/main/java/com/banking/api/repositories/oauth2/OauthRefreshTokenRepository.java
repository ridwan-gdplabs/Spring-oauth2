package com.banking.api.repositories.oauth2;

import org.springframework.data.repository.CrudRepository;

import com.banking.api.models.oauth2.OauthRefreshToken;

public interface OauthRefreshTokenRepository extends CrudRepository<OauthRefreshToken, String> {

}
