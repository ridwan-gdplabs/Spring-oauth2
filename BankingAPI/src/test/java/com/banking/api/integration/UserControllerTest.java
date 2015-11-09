package com.banking.api.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.api.BankingApiApplicationTests;
import com.banking.api.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends BankingApiApplicationTests {

    @Test
    public void getUserData_validUser_responseOk() {
        User usersResult = getRestTemplate().getForObject(
                                    getBaseURL().toString() + "me", 
                                    User.class);
        assertThat(usersResult.getUsername(), equalTo("user1"));
        assertThat(usersResult.getName(), equalTo("User User"));
    }
    
}
