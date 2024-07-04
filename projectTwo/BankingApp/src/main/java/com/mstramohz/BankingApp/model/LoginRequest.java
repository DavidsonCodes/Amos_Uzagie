package com.mstramohz.BankingApp.model;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
