package com.shreya.mapper;

import com.shreya.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shreya on 10/1/17.
 */
public class UserInfoMapper implements RowMapper<UserInfo> {

    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        String username = rs.getString("Username");
        String password = rs.getString("Password");
        String email=rs.getString("EmailId");
        Date dob=rs.getDate("DateOfBirth");
        String type=rs.getString("type");

        return new UserInfo(username, password,email,dob,type);
    }

}