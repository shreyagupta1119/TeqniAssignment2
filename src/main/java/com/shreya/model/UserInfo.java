package com.shreya.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by shreya on 7/1/17.
 */
@Data
@Table(name="userinfo")
@NoArgsConstructor
public class UserInfo {
    @Id
    private String username;
    private String password;
    private String email;
    private Date dob;
    private String type;

    public UserInfo(String username, String password, String email, Date dob, String type){
        this.username=username;
        this.password=password;
        this.email=email;
        this.dob=dob;
        this.type=type;

    }


}
