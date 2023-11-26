package com.bank.server.models;

public record RegisterForm(String email, String password, String username) {

    @Override
    public String toString() {
        return "RegisterForm [email="+email() + ", password=" + password() + ", username=" +  username() + "]";
    }

    
}
