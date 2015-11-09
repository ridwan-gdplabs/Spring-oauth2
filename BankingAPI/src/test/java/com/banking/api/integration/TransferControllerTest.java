package com.banking.api.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.banking.api.BankingApiApplicationTests;
import com.banking.api.models.Transfer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.banking.api.errors.Error;
import com.banking.api.http.requests.TransferPost;


@RunWith(SpringJUnit4ClassRunner.class)
public class TransferControllerTest extends BankingApiApplicationTests {

    @Test
    public void doTransfer_validParam_responseOk() {
        TransferPost transferPost = new TransferPost(10.00, "0987654321");
        ResponseEntity<Transfer> transferResult = getRestTemplate().postForEntity(
                                        getBaseURL().toString() + "accounts/1234567890/transfers", 
                                        transferPost, Transfer.class);
        assertThat(transferResult.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(transferResult.getBody().getAmount(), equalTo(10.00));
        assertThat(transferResult.getBody().getRecipient(), equalTo("0987654321"));
        assertThat(transferResult.getBody().getSender(), equalTo("1234567890"));
        
    }
    
    @Test
    public void getTransferHistories_validParam_responseOk() {
        Transfer[] transferHistories = getRestTemplate().getForObject(
                                        getBaseURL().toString() + "accounts/0987654321/transfers", 
                                        Transfer[].class);
        assertThat(transferHistories[0].getAmount(), equalTo(15.00));
        assertThat(transferHistories[0].getRecipient(), equalTo("1234567890"));
        assertThat(transferHistories[0].getSender(), equalTo("0987654321"));
    }
    
    @Test
    public void getTransferHistories_invalidSender_responseNotFound () 
                        throws JsonParseException, JsonMappingException, IOException {
        try {
            ResponseEntity<Transfer[]> transferHistories = getRestTemplate().getForEntity(
                                getBaseURL().toString() + "accounts/1/transfers", 
                                Transfer[].class);
            assertThat(transferHistories.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
            
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            ObjectMapper obj = new ObjectMapper();
            Error error = obj.readValue(responseBody, Error.class);
            
            assertThat(e.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
            assertThat(error.getCode(), equalTo(4));
            assertThat(error.getMessage(), equalTo("Account 1 is not found"));
        }
        
    }
    
    @Test
    public void DoTransfer_EmptyParam_ResponseBadRequest() {
        try {
            ResponseEntity<Transfer> transferResult = getRestTemplate().postForEntity(
                    getBaseURL().toString() + "accounts/1234567890/transfers", null, Transfer.class);
            assertThat(transferResult.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
            
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        }
    }
    
    @Test
    public void DoTransfer_BalanceLessThanOrEqualZero_ResponseBadRequest() 
                        throws JsonParseException, JsonMappingException, IOException {
        try {
            TransferPost transferPost = new TransferPost(-1.00, "0987654321");
            ResponseEntity<Transfer> transferResult = getRestTemplate().postForEntity(
                                            getBaseURL().toString() + "accounts/1234567890/transfers", 
                                            transferPost, Transfer.class);
            assertThat(transferResult.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            ObjectMapper obj = new ObjectMapper();
            Error error = obj.readValue(responseBody, Error.class);
            
            assertThat(e.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
            assertThat(error.getCode(), equalTo(1));
            assertThat(error.getMessage(), equalTo("Balance must be greater than zero"));
        }
    }
    
    @Test
    public void doTransfer_insufficientSenderBalance_responseBadRequest() 
                        throws JsonParseException, JsonMappingException, IOException {
        try {
            TransferPost transferPost = new TransferPost(10000000.00, "0987654321");
            ResponseEntity<Transfer> transferResult = getRestTemplate().postForEntity(
                                            getBaseURL().toString() + "accounts/1234567890/transfers", 
                                            transferPost, Transfer.class);
            assertThat(transferResult.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            ObjectMapper obj = new ObjectMapper();
            Error error = obj.readValue(responseBody, Error.class);
            
            assertThat(e.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
            assertThat(error.getCode(), equalTo(2));
            assertThat(error.getMessage(), equalTo("Insufficient balance account 1234567890"));
        }
    }
    
    @Test
    public void doTransfer_loopbackTransfer_responseBadRequest() 
                        throws JsonParseException, JsonMappingException, IOException {
        try {
            TransferPost transferPost = new TransferPost(10.11, "0987654321");
            ResponseEntity<Transfer> transferResult = getRestTemplate().postForEntity(
                                            getBaseURL().toString() + "accounts/0987654321/transfers", 
                                            transferPost, Transfer.class);
            assertThat(transferResult.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            ObjectMapper obj = new ObjectMapper();
            Error error = obj.readValue(responseBody, Error.class);
            
            assertThat(e.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
            assertThat(error.getCode(), equalTo(5));
            assertThat(error.getMessage(), equalTo("Loopback transfer"));
        }
    }
}
