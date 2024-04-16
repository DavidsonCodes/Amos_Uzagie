package com.mstramohz.BankingApp.controller;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.services.AccountUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountUserController {

    @Autowired
    private AccountUserService accountUserService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<AccountUser>> getAllUsers(){
        return accountUserService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AccountUser> getUserById(@PathVariable int id){
        return accountUserService.getUserById(id);
    }

    @PostMapping("/newUser")
    public ResponseEntity<AccountUser> postUser(@RequestBody @Valid AccountUser user){
        return accountUserService.postUser(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountUser> updateUser(@PathVariable int id, @RequestBody @Valid AccountUser user){
        return accountUserService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccountUser> deleteUser(@PathVariable int id){
        return accountUserService.deleteUser(id);
    }
}
