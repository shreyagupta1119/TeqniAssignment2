package com.shreya.dao;

import com.shreya.model.UserInfo;

import java.util.List;

/**
 * Created by shreya on 10/1/17.
 */
public interface UserInfoDAO {

    public UserInfo findUserInfo(String userName);

// [USER,ADMIN,..]
    public List<String> getUserRoles(String userName);

    public void addUser(UserInfo user);


}