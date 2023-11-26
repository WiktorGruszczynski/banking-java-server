package com.bank.server.models;

import java.util.Date;


import com.bank.server.tools.TokenEncoder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthToken {
    private String token;
    private String email;
    private String accountId;
    private Date expires;

    @JsonIgnore
    private TokenEncoder tokenEncoder = new TokenEncoder();

    public AuthToken(String token){
        this.token = token;
        this.email = getEmail();
        this.accountId = getAccountId();
    }

    public AuthToken(String email, String accountId, Date expires){
        this.token = tokenEncoder.encrypt(email)+";"+ tokenEncoder.encrypt(accountId) +";"+tokenEncoder.encrypt(expires.toString());
        this.expires = expires;
    }

    @JsonIgnore
    public String getEmail(){
        if (email==null){
            return tokenEncoder.decrypt(token.split(";")[0]);
        }
        return email;
    }

    @JsonIgnore
    public String getAccountId(){
        if (accountId==null){
            return tokenEncoder.decrypt(token.split(";")[1]);
        }
        return accountId;
    }

    @JsonIgnore
    public void getExpirationDate(){
        System.out.println(tokenEncoder.decrypt(token.split(";")[2]));
    }

}
