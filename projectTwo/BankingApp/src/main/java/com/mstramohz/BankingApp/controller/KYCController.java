package com.mstramohz.BankingApp.controller;


import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.KnowYourCustomer;
import com.mstramohz.BankingApp.services.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class KYCController {
    @Autowired
    private KYCService kycService;

    @GetMapping("/kyc")
    public ResponseEntity<List<KnowYourCustomer>> getAllKYC(){
        return kycService.getAllKYC();
    }

    @GetMapping("/kyc/{id}")
    public ResponseEntity<KnowYourCustomer> getById(@PathVariable int id){
        return kycService.getById(id);
    }

    public ResponseEntity<KnowYourCustomer> getByUser(AccountUser user){
        return kycService.getByUser(user);
    }


    public ResponseEntity<KnowYourCustomer> createKYC(KnowYourCustomer kyc){
        return kycService.createNewKYC(kyc);
    }

    public ResponseEntity<KnowYourCustomer> updateKYC(int id, KnowYourCustomer kyc){
        return kycService.updateKYC(id, kyc);
    }

    public ResponseEntity<KnowYourCustomer> deleteKYC(int id){
        return kycService.deleteKYC(id);
    }
}
