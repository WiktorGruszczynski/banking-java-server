package com.bank.server.models;


import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 26)
    private String accountId;
    
    private String email;
    private String password;
    private String username;
    private Long balance;


    public User(String email, String password, String username, Long balance) {
        this.accountId = generateUniqueId();
        this.email = email;
        this.password = password;
        this.username = username;
        this.balance = balance;
    }

    private String generateUniqueId() {
        // Generate a random string of 26 digits
        Random random = new Random();
        StringBuilder sb = new StringBuilder(26);
        for (int i = 0; i < 26; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
