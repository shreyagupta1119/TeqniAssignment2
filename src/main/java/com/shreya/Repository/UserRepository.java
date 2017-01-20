package com.shreya.Repository;

import com.shreya.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by shreya on 14/1/17.
 */

@Repository
public class UserRepository extends JdbcDaoSupport {

    JdbcTemplate template=new JdbcTemplate();
    @Autowired
    public UserRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void addUser(UserInfo user){
        String sql="insert into userinfo "+ "values(?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getDob(),user.getDob());
    }
}
