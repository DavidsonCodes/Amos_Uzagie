package com.mstramohz.BankingApp.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class AccountUserResource extends RepresentationModel<AccountUserResource> {
    private AccountUser accountUser;
}
