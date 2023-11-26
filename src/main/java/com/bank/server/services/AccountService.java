package com.bank.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.server.dao.UserDao;
import com.bank.server.models.Account;
import com.bank.server.models.AuthToken;
import com.bank.server.models.ResultEntity;
import com.bank.server.models.User;

@Service
public class AccountService {
    @Autowired
    private UserDao userDao;

    public ResultEntity<Account> getAccountDetails(String tokenString){
        AuthToken authToken = new AuthToken(tokenString);
        String accountId = authToken.getAccountId();



        if (accountId!=null){
            User user = userDao.findByAccountId(accountId);

            if (user!=null){
                Account account = new Account(user.getEmail(), user.getUsername(), accountId, user.getBalance());

                return new ResultEntity<Account>(null, account);
            }
        }

        return new ResultEntity<>(false);
        
    }
}
