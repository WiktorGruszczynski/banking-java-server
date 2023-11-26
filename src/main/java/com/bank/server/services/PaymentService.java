package com.bank.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.server.dao.UserDao;
import com.bank.server.models.AuthToken;
import com.bank.server.models.PaymentForm;
import com.bank.server.models.ResultEntity;
import com.bank.server.models.User;

@Service
public class PaymentService {
    @Autowired
    private UserDao userDao;

    public ResultEntity<String> transferFunds(PaymentForm paymentForm){
        User sender = userDao.findByAccountId(paymentForm.senderId());
        User receiver = userDao.findByAccountId(paymentForm.receiverId());
        Long amount = paymentForm.amount();
        AuthToken authToken = new AuthToken(paymentForm.token());


        if (receiver==null)   return new ResultEntity<>(false, "invalid receiver id");
        if (sender==null)    return new ResultEntity<>(false, "invalid sender id");
        if (receiver.getId()==sender.getId()) return new ResultEntity<>(false, "invalid receiver id");


        if (!authToken.getAccountId().equals(sender.getAccountId())) return new ResultEntity<>(false, "acces forbidden");
        if (amount<=0)      return new ResultEntity<>(false, "invalid amount");

        if (sender.getBalance() >= amount){

            Long senderBalanceAfterTransaction = sender.getBalance() - amount;
            Long receiverBalanceAfterTransaction =  receiver.getBalance() + amount;

            sender.setBalance(senderBalanceAfterTransaction);
            receiver.setBalance(receiverBalanceAfterTransaction);

            List<User> userList = new ArrayList<>();
            
            userList.add(sender);
            userList.add(receiver);

            userDao.saveAll(userList);

            return new ResultEntity<>(true);
        }
        else{
            return new ResultEntity<>(false, "not enough funds");
        }
    }

}
