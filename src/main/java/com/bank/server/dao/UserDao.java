package com.bank.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.server.models.User;


public interface UserDao extends JpaRepository<User, Long>{
    User findByEmail(String email);

    User findByAccountId(String accountId);
}
