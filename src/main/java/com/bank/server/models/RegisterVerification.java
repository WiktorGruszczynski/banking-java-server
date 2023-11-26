package com.bank.server.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String username;
    private String code;
    private Date expires;


    public RegisterVerification(String email, String password, String username, String code, Date expires) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.code = code;
        this.expires = expires;
    }
}
