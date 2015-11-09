package com.banking.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.banking.api.models.Transfer;
import com.banking.api.models.TransferTest;

public interface TransferRepository extends CrudRepository<Transfer, String> {

    List<Transfer> findBySender(String sender);

    @Query(nativeQuery=true,value="SELECT t.transaction_id, t.date_time, t.amount, t.sender FROM transfer t")
    List<TransferTest> findTest();
    
}
