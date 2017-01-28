package com.shreya.web;

import com.shreya.model.UserInfo;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

/**
 * Created by shreya on 20/1/17.
 */
@Data
@Table(name="login")
public class Login {
    @Id
    String username;
    String password;
    String salt;
    String md5;
    String sha1;
    String sha256;

    public Login(UserInfo user){
        username=user.getUsername();
        password=user.getPassword();
    }
}