package com.banking.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banking.api.models.Account;
import com.banking.api.models.User;

public interface AccountRepository extends CrudRepository<Account, String> {

    List<Account> findByUsers(User user);

    Account findByAccountId(String accountId);

    
}
