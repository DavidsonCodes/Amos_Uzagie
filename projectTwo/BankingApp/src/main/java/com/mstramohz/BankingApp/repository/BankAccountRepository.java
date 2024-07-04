package com.mstramohz.BankingApp.repository;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByUser(AccountUser user);
}
