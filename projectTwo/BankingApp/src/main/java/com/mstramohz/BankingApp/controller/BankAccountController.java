package com.mstramohz.BankingApp.controller;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.BankAccount;
import com.mstramohz.BankingApp.services.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private BankAccountService bankAccountService;

    @GetMapping("")
    public ResponseEntity<List<BankAccount>> getAllAccounts(){
        return bankAccountService.getAllAcc();
    }

    @GetMapping("/{}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable int id){
        return bankAccountService.getAccById(id);
    }

    public ResponseEntity<BankAccount> getByUser(AccountUser user){
        return bankAccountService.getAccByUser(user);
    }

    @PostMapping("/opening_balance")
    public ResponseEntity<BankAccount> createAccount(@RequestBody @Valid AccountUser user){
        return bankAccountService.createNewAcc(user);
    }
    @PostMapping("/opening_balance")
    public ResponseEntity<BankAccount> createAccount(@RequestBody @Valid AccountUser user, @Valid @RequestParam double openingAmount){
        return bankAccountService.createNewAcc(user);
    }

    public ResponseEntity<BankAccount> updateAccount(int id, BankAccount account){
        return bankAccountService.updateAcc(id, account);
    }

    public ResponseEntity<BankAccount>closeAccount(AccountUser user){
        return bankAccountService.closeAcc(user);
    }

}
