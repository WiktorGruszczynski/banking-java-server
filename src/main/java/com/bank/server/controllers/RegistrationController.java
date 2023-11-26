package com.bank.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.server.models.ConfirmationRegisterForm;
import com.bank.server.models.RegisterForm;
import com.bank.server.models.ResultEntity;
import com.bank.server.services.RegisterService;

@RestController
@RequestMapping(path = "api/register")
public class RegistrationController {
    @Autowired
    public RegisterService registerService;
    
    @PostMapping(path = "/add")
    public ResultEntity<?> registerNewUser(@RequestBody RegisterForm registerForm)
    {
        return new ResultEntity<>(registerService.createUserVerification(registerForm));
    }

    @PostMapping(path = "/confirm")
    public ResultEntity<?> confirmUserRegistration(@RequestBody ConfirmationRegisterForm confirmationRegisterForm){
        return new ResultEntity<>(registerService.confirmAndRegisterUser(confirmationRegisterForm));
    }
}
