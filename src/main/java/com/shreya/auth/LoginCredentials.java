package com.shreya.auth;

import com.shreya.model.UserInfo;
import lombok.Data;

/**
 * Created by shreya on 25/1/17.
 */
@Data
public class LoginCredentials {
    String username;
    String password;

    public LoginCredentials(){}

    public  LoginCredentials(UserInfo user){
        this.username=user.getUsername();
        this.password=user.getPassword();
    }
}