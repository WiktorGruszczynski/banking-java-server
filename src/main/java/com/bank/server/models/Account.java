package com.bank.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String ownerEmail;
    private String ownerUsername;
    private String accountId;
    private Long balance;
}
