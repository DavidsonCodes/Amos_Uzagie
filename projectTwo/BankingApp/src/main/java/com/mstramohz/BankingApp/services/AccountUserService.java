package com.mstramohz.BankingApp.services;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.repository.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AccountUserService {

    @Autowired
    private AccountUserRepository accountUserRepository;

    public ResponseEntity<List<AccountUser>> getAllUsers(){
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> getUserById(int id){
        try{
            return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


    public ResponseEntity<AccountUser> postUser(AccountUser user){
        return new ResponseEntity<>(accountUserRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<AccountUser> updateUser(int id, AccountUser user){
        AccountUser update = accountUserRepository.findById(id).get();
        update.setFirstName(user.getFirstName());
        update.setLastName(user.getLastName());
        update.setMiddleName(user.getMiddleName());
        update.setUserName(user.getUserName());
        update.setPassword(user.getPassword());
        update.setPhoneNumber(user.getPhoneNumber());
        return new ResponseEntity<>(accountUserRepository.save(update), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> deleteUser(int id) {
        AccountUser user = accountUserRepository.findById(id).get();
        accountUserRepository.delete(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
