package com.banking.api.repositories.oauth2;

import org.springframework.data.repository.CrudRepository;

import com.banking.api.models.oauth2.OauthCode;

public interface OauthCodeRepository extends CrudRepository<OauthCode, String> {

}
