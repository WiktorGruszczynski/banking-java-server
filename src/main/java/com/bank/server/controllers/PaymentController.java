package com.bank.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.server.models.PaymentForm;
import com.bank.server.models.ResultEntity;
import com.bank.server.services.PaymentService;

@RestController
@RequestMapping(path = "api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping(path = "/transfer")
    public ResultEntity<String> transferFunds(@RequestBody PaymentForm paymentForm){
        return paymentService.transferFunds(paymentForm);
    }
}
