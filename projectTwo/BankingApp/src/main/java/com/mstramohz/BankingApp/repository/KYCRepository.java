package com.mstramohz.BankingApp.repository;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.KnowYourCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCRepository extends JpaRepository<KnowYourCustomer, Integer> {
    KnowYourCustomer findByUser(AccountUser user);
}
