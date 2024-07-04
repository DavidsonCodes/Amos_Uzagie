package com.mstramohz.BankingApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "kyc")
@Getter
@RequiredArgsConstructor
public class KnowYourCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String address;

    @Setter
    private String bvn;

    @Setter
    private String nin;

    @Setter
    private String lga;

    @Setter
    private String state;

    @Setter
    private String dob;

    @Setter
    private String nextOfKin;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;
}
