package com.mstramohz.BankingApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Entity(name = "accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Pattern(regexp = "^\\+?\\d{1,15}$")
    private String accountNumber;

    @Setter
    private double accountBalance;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;

    public BankAccount(){}
    public BankAccount (String accountNumber, double accountBalance, AccountUser user){
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.user = user;
    }
}
