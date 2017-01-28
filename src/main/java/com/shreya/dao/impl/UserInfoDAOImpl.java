package com.shreya.dao.impl;

import com.shreya.dao.UserInfoDAO;
import com.shreya.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        this.getJdbcTemplate().update(sql,user.getUsername(),user.getEmail(),user.getPassword(),user.getDob(),user.getType());
    }


    @Override
    public UserInfo findUserInfo(String userName) {
        String sql = "Select username,email,password,dob,type"//
                + " from userinfo where username = ? ";

        return this.getJdbcTemplate().query(sql, new Object[]{userName}, new ResultSetExtractor<UserInfo>(){
            @Override
            public UserInfo extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    UserInfo user= new UserInfo();
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setDob(rs.getDate("dob"));
                    user.setType(rs.getString("type"));

                    return user;
                }
                return null;
            }

        });
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