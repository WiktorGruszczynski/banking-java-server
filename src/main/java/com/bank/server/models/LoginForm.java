package com.bank.server.models;

public record LoginForm(String email, String password) {

    @Override
    public String toString() {
        return "LoginForm [email="+email()+", password="+password()+"]";
    }

}
