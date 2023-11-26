package com.bank.server.models;

public record PaymentForm(String token, String senderId, String receiverId, Long amount) {
    
}
