package com.banking.api.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.api.BankingApiApplicationTests;
import com.banking.api.models.Account;


@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest extends BankingApiApplicationTests {

    @Test
    public void getAccounts_validUser_responseOk() {
        Account[] accountList = getRestTemplate().getForObject(
                                        getBaseURL().toString() + "accounts",
                                        Account[].class);
        
        assertThat(accountList[0].getAccountId(), equalTo("0987654321"));
        assertThat(accountList[0].getBalance(), equalTo(500.00));
        assertThat(accountList[1].getAccountId(), equalTo("1234567890"));
        assertThat(accountList[1].getBalance(), equalTo(100.00));
    }
    
}
