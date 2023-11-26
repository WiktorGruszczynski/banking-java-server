package com.bank.server.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.server.dao.RegisterVerificationDao;
import com.bank.server.dao.UserDao;
import com.bank.server.models.ConfirmationRegisterForm;
import com.bank.server.models.RegisterForm;
import com.bank.server.models.RegisterVerification;
import com.bank.server.models.User;
import com.bank.server.tools.Email;
import com.bank.server.tools.ExpirationGenerator;
import com.bank.server.tools.RandomStringGenerator;

@Service
public class RegisterService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterVerificationDao registerVerificationDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Email emailSender;


    private void sendEmail(String email, String code){
        emailSender.sendSimpleMessage(email, "verification code", code);
    }

    private boolean isUserRegistered(String email)
    {
        User foundUser = userDao.findByEmail(email);
        if (foundUser==null){
            return false;
        }
        return true;
    }
    

    public boolean createUserVerification(RegisterForm registerForm){
        if (isUserRegistered(registerForm.email())){
            return false;
        }
        else{
            String code = RandomStringGenerator.generateRandomCode(6);
            Date expires = ExpirationGenerator.getDateIn15Minutes();
            String hashedPassword = passwordEncoder.encode(registerForm.password());


            RegisterVerification registerVerification = new RegisterVerification(registerForm.email(), hashedPassword, registerForm.username(), code, expires);
            registerVerificationDao.save(registerVerification);


            new Thread(){
                @Override
                public void run(){
                    sendEmail(registerForm.email(), code);
                }
            }.start();

            

            return true;
        }
    }

    private boolean isExpired(Date date){
        return new Date().compareTo(date)>0;
    }

    public boolean confirmAndRegisterUser(ConfirmationRegisterForm confirmationRegisterForm){
        String email = confirmationRegisterForm.email();
        RegisterVerification registerVerification = registerVerificationDao.findByEmail(email);


        //if registration code is equal
        if (registerVerification.getCode().equals(confirmationRegisterForm.code()) && !isExpired(registerVerification.getExpires())){
            registerVerificationDao.deleteByEmail(email);

            Long defaultBalance = 1_000_00l;

            User user = new User(email, registerVerification.getPassword(), registerVerification.getUsername(), defaultBalance);
            userDao.save(user);

            return true;
        }
        else {
            return false;
        }
    }
}
