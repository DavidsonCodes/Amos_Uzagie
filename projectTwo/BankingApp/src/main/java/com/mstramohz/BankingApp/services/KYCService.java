package com.mstramohz.BankingApp.services;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.KnowYourCustomer;
import com.mstramohz.BankingApp.repository.KYCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KYCService {
    @Autowired
    private final KYCRepository kycRepository;

    public ResponseEntity<List<KnowYourCustomer>> getAllKYC(){
        return new ResponseEntity<>(kycRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<KnowYourCustomer> getById(int id){
        return new ResponseEntity<>(kycRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<KnowYourCustomer> getByUser(AccountUser user){
        return new ResponseEntity<>(kycRepository.findByUser(user), HttpStatus.OK);
    }

    public ResponseEntity<KnowYourCustomer> createNewKYC(KnowYourCustomer kyc){
        return new ResponseEntity<>(kycRepository.save(kyc), HttpStatus.CREATED);
    }

    public ResponseEntity<KnowYourCustomer> updateKYC(int id, KnowYourCustomer kyc){
        KnowYourCustomer kycUpdate = kycRepository.findById(id).get();
        kycUpdate.setAddress(kyc.getAddress());
        kycUpdate.setUser(kyc.getUser());
        kycUpdate.setBvn(kyc.getBvn());
        kycUpdate.setLga(kyc.getLga());
        kycUpdate.setDob(kyc.getDob());
        kycUpdate.setNin(kyc.getNin());
        kycUpdate.setState(kyc.getState());
        kycUpdate.setNextOfKin(kyc.getNextOfKin());
        return new ResponseEntity<>(kycRepository.save(kycUpdate), HttpStatus.OK);
    }

    public ResponseEntity<KnowYourCustomer> deleteKYC(int id){
        KnowYourCustomer kyc = kycRepository.findById(id).get();
        kycRepository.delete(kyc);
        return new ResponseEntity<>(kyc, HttpStatus.OK);
    }
}
