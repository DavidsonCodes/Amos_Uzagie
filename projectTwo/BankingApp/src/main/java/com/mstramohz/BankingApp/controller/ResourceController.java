package com.mstramohz.BankingApp.controller;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.AccountUserResource;
import com.mstramohz.BankingApp.services.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private AccountUserService accountUserService;

    public ResponseEntity<AccountUserResource> GetAccountResources(int id){
        AccountUser requestedUser = accountUserService.getUserById(id).getBody();
        AccountUserResource userResource = new AccountUserResource();
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getUserById(id)).withSelfRel();
        Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteUser(id)).withRel("delete");
        Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateUser(id, requestedUser)).withRel("update");
        Link allUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllUsers()).withRel("allUsers");
        userResource.add(selfLink, delete, update, allUsers);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }
}
