package com.mstramohz.BankingApp.controller;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.AccountUserResource;
import com.mstramohz.BankingApp.model.LoginRequest;
import com.mstramohz.BankingApp.model.LoginResponse;
import com.mstramohz.BankingApp.services.AccountUserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AccountUserController {

    @Autowired
    private final AccountUserService accountUserService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(LoginRequest request) throws MessagingException{
        return accountUserService.authenticate(request);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountUser>> getAllUsers(){
        return accountUserService.getAllAccUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountUser> getUserById(@PathVariable int id){
        return accountUserService.getUserById(id);
    }

    @GetMapping("/name")
    public ResponseEntity<AccountUser> getByUserName(@RequestParam String username){
        return accountUserService.getByUsername(username);
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountUser> createNewUser(@RequestBody @Valid AccountUser user){
        return accountUserService.createNewUser(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountUser> updateAccUser(@PathVariable int id, @RequestBody @Valid AccountUser user){
        return accountUserService.updateAccUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccountUser> deleteAccUser(@PathVariable int id){
        return accountUserService.deleteAccUser(id);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<AccountUserResource> getAccResource(@PathVariable int id){
        AccountUserResource resource = new AccountUserResource();
        AccountUserController userService;
        AccountUser user = accountUserService.getUserById(id).getBody();
        resource.setAccountUser(user);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getUserById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateAccUser(id, user)).withRel("update");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteAccUser(id)).withRel("deleteUser");
        Link getAll = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllUsers()).withRel("getAll");

        resource.add(selfLink, updateLink, deleteLink, getAll);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
