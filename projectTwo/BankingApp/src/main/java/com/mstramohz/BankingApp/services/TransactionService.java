package com.mstramohz.BankingApp.services;

import com.mstramohz.BankingApp.model.Transactions;
import com.mstramohz.BankingApp.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    public ResponseEntity<List<Transactions>> getAllTransaction(){
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Transactions> getById(long id){
        return new ResponseEntity<>(transactionRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Transactions> getByTransactionId(String transactionId){
        return new ResponseEntity<>(transactionRepository.findByTransactionId(transactionId), HttpStatus.OK);
    }

    public ResponseEntity<Transactions> postTransaction(Transactions transaction){
        return new ResponseEntity<>(transactionRepository.save(transaction), HttpStatus.CREATED);
    }
}
