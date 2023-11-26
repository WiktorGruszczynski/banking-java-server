package com.bank.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.server.models.Account;
import com.bank.server.models.ResultEntity;
import com.bank.server.services.AccountService;




@RestController
@RequestMapping(path = "api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResultEntity<Account> getAccountDetails(@RequestParam String token){
        return accountService.getAccountDetails(token);
    }
}
