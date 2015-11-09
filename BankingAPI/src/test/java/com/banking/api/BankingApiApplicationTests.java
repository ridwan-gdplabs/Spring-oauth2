package com.banking.api;


import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.ActiveProfiles;

import com.banking.api.models.Account;
import com.banking.api.models.Transfer;
import com.banking.api.models.User;
import com.banking.api.models.oauth2.Authority;
import com.banking.api.models.oauth2.OauthClientDetail;
import com.banking.api.repositories.AccountRepository;
import com.banking.api.repositories.TransferRepository;
import com.banking.api.repositories.UserRepository;
import com.banking.api.repositories.oauth2.AuthorityRepository;
import com.banking.api.repositories.oauth2.OauthClientDetailRepository;

@SpringApplicationConfiguration(classes = BankingApiApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
@ActiveProfiles("test")
public class BankingApiApplicationTests {
    
    @Value("${local.server.port}")
    private int port;
    
    @Autowired
    private AccountRepository accountRepository;
    private Account account;
    private Account account2;
    
    @Autowired
    private OauthClientDetailRepository oauthClientDetailRepository;
    private OauthClientDetail oauthClientDetail;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    private Authority authority;
    
    @Autowired
    private UserRepository userRepository;
    private User user;
    
    @Autowired
    private TransferRepository transferRepository;
    private Transfer transfer;
    
    private OAuth2RestOperations restTemplate;
    private URL baseURL;
    
    @Before
    public void setUp() throws Exception {
        this.baseURL = new URL("http://localhost:" + port);
        
        this.setResourceTemplate();
        
        this.deleteAllTables();
        
        this.addOauth_client_details();
        this.addAccountsUsers();
        this.addAuthorities();
        this.addTransfer();
    }
    
    public void setResourceTemplate() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername("user1");
        resourceDetails.setPassword("user1");
        resourceDetails.setAccessTokenUri(baseURL.toString() + "/oauth/token");
        resourceDetails.setClientId("testID");
        resourceDetails.setClientSecret("testSecret");
        
        restTemplate = new OAuth2RestTemplate(resourceDetails);
    }
    
    public void deleteAllTables() {
        oauthClientDetailRepository.deleteAll();
        transferRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
        authorityRepository.deleteAll();
    }
    
    public void addOauth_client_details() {
        oauthClientDetail = new OauthClientDetail("testID", 
                                                  "test_resource",
                                                  "testSecret",
                                                  "write",
                                                  "password",
                                                  "",
                                                  "client",
                                                  3600,
                                                  0,
                                                  "",
                                                  null);
        oauthClientDetailRepository.save(oauthClientDetail);
    }
    
    public void addAccountsUsers() {
        user = new User("user1", "user1", 1, "User User");
        userRepository.save(user);
        
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        account  = new Account("1234567890", 100.00, userList);
        account2 = new Account("0987654321", 500.00, userList); 
        accountRepository.save(account);
        accountRepository.save(account2);
        
        List<Account> accountList = new ArrayList<Account>();
        accountList.add(account);
        accountList.add(account2);
        user.setAccounts(accountList);
        userRepository.save(user);
    }
    
    public void addAuthorities() {
        authority = new Authority("user1", "ROLE_user");
        authorityRepository.save(authority);
    }
    
    public void addTransfer() {
        transfer = new Transfer(Calendar.getInstance().getTime(), 
                                15.00, 
                                "0987654321", 
                                "1234567890");
        transferRepository.save(transfer);
    }
    
    public int getPort() {
        return port;
    }
    
    public OAuth2RestOperations getRestTemplate() {
        return restTemplate;
    }
    
    public URL getBaseURL() {
        return baseURL;
    }
}
