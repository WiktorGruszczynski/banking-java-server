package com.bank.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.server.models.AuthToken;
import com.bank.server.models.LoginForm;
import com.bank.server.models.ResultEntity;
import com.bank.server.services.LoginService;

@RestController
@RequestMapping(path = "api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResultEntity<AuthToken> loginUser(@RequestBody LoginForm loginForm){
        return loginService.loginUser(loginForm);
    }
}
