package com.mstramohz.BankingApp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Setter
@Getter
public class AccountUserResource extends RepresentationModel<AccountUserResource> {
    private AccountUser accountUser;
}
