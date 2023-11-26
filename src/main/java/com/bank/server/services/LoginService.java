package com.bank.server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.bank.server.dao.UserDao;
import com.bank.server.models.AuthToken;
import com.bank.server.models.LoginForm;
import com.bank.server.models.ResultEntity;
import com.bank.server.models.User;
import com.bank.server.tools.ExpirationGenerator;

@Service
public class LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private boolean isPasswordCorrect(String inputPassword, String storedPassword){
        return passwordEncoder.matches(inputPassword, storedPassword);
    }


    public ResultEntity<AuthToken> loginUser(LoginForm loginForm){
        User user = userDao.findByEmail(loginForm.email());

        if (user!=null && isPasswordCorrect(loginForm.password(), user.getPassword())){
            AuthToken authToken = new AuthToken(loginForm.email(), user.getAccountId(), ExpirationGenerator.getDateIn60Minutes());

            return new ResultEntity<AuthToken>(true, authToken);
        }
        else{
            return new ResultEntity<>(false);
        }
    }
}
