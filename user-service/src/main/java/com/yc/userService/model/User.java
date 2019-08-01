package com.yc.userService.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: star
 * @description: user表实体类
 * @author: Zwx
 * @create: 2019-06-28 15:58
 **/
@Data
public class User implements Serializable {
    private int ID;
    private String userName;
    private String password;
    private String mobile;
    private Integer energyValue;
    private String realName;
    private Integer level;
    private Integer enableFlag;
    private Integer vipFlag;
    private Integer vipLevel;
    private Date registerTime;
    private Date lastLoginTime;
    private String avatar;
    private String signature;
    private String sex;

    public User() {
    }

    public User(String userName, String mobile, Date registerTime) {
        this.userName = userName;
        this.mobile = mobile;
        this.registerTime = registerTime;
    }
}
