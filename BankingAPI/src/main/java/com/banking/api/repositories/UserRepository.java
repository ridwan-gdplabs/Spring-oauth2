package com.banking.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.banking.api.models.User;

public interface UserRepository extends CrudRepository<User, String> {

}
