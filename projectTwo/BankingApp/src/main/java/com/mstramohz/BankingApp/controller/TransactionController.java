package com.mstramohz.BankingApp.controller;

import com.mstramohz.BankingApp.model.Transactions;
import com.mstramohz.BankingApp.repository.TransactionRepository;
import com.mstramohz.BankingApp.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @GetMapping("")
    public ResponseEntity<List<Transactions>> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("{id}")
    public ResponseEntity<Transactions> getById(long id){
        return transactionService.getById( id);
    }

    @GetMapping("")
    public ResponseEntity<Transactions> getByTransactionId(@PathVariable String transactionId){
        return transactionService.getByTransactionId(transactionId);
    }

    @PutMapping("")
    public ResponseEntity<Transactions> postTransaction(@RequestBody @Valid Transactions transaction){
        return transactionService.postTransaction(transaction);
    }
}
