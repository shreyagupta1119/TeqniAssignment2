package com.shreya.service;

import com.shreya.auth.LoginCredentials;
import com.shreya.dao.UserInfoDAO;
import com.shreya.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by shreya on 20/1/17.
 */
@Component
public class LoginService {

    @Autowired
    public UserInfoDAO userInfoDAO;

    @SuppressWarnings("unused")
    public LoginService() {}


    public UserInfo login(LoginCredentials credentials) {
        UserInfo user=userInfoDAO.findUserInfo(credentials.getUsername());
        if(user.getPassword().equals(credentials.getPassword()))
            return user;
        else
            return null;

    }
}