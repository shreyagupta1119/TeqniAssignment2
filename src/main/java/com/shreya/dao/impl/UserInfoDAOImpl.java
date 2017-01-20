package com.shreya.dao.impl;

import com.shreya.dao.UserInfoDAO;
import com.shreya.mapper.UserInfoMapper;
import com.shreya.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by shreya on 10/1/17.
 */
@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

    @Autowired
    public UserInfoDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void addUser(UserInfo user){
        String sql="insert into userinfo "+ "values(?,?,?,?,?)";
        this.getJdbcTemplate().update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getDob(),user.getDob());
    }


    @Override
    public UserInfo findUserInfo(String userName) {
        String sql = "Select u.Username,u.Password,u.EmailId,u.DateOfBirth,u.type"//
                + " from Users u where u.Username = ? ";

        Object[] params = new Object[] { userName };
        UserInfoMapper mapper = new UserInfoMapper();
        try {
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Username = ? ";

        Object[] params = new Object[] { userName };

        List<String> roles = this.getJdbcTemplate().queryForList(sql,params, String.class);

        return roles;
    }

}