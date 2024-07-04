package com.mstramohz.BankingApp.services;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.BankAccount;
import com.mstramohz.BankingApp.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public ResponseEntity<List<BankAccount>> getAllAcc(){
        return new ResponseEntity<>(bankAccountRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> getAccById(int id){
        return new ResponseEntity<>(bankAccountRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> getAccByUser(AccountUser user){
        return new ResponseEntity<>(bankAccountRepository.findByUser(user), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> createNewAcc(AccountUser user){
        StringBuilder accNum = new StringBuilder();
        int count = 0;
        while (count < accNum.length()){
            int randomInt = new Random().nextInt(10);
            accNum.append(randomInt);
        }
        BankAccount account = new BankAccount(accNum.toString(), 0.00, user);
        return new ResponseEntity<>(bankAccountRepository.save(account), HttpStatus.CREATED);
    }
    public ResponseEntity<BankAccount> createAccount(AccountUser user, double openingAmount){
        StringBuilder accNum = new StringBuilder();
        int count = 0;
        while (count < accNum.length()){
            int randomInt = new Random().nextInt(10);
            accNum.append(randomInt);
        }
        BankAccount account = new BankAccount(accNum.toString(), openingAmount, user);
        return new ResponseEntity<>(bankAccountRepository.save(account), HttpStatus.CREATED);
    }

    public ResponseEntity<BankAccount> updateAcc(int id, BankAccount account){
        BankAccount newUpdate = bankAccountRepository.findById(id).get();
        newUpdate.setAccountNumber(account.getAccountNumber());
        newUpdate.setAccountBalance(account.getAccountBalance());
        newUpdate.setUser(account.getUser());
        return new ResponseEntity<>(bankAccountRepository.save(newUpdate), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> closeAcc(AccountUser user){
        BankAccount accUser = bankAccountRepository.findByUser(user);
        bankAccountRepository.delete(accUser);
        return new ResponseEntity<>(accUser, HttpStatus.OK);
    }
}
