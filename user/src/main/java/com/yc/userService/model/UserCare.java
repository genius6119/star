package com.yc.userService.model;

import lombok.Data;

import java.util.Date;

/**
 * @program: star
 * @description: user_care表
 * @author: Zwx
 * @create: 2019-07-06 15:37
 **/
@Data
public class UserCare {
    private int id;
    private int userId;
    private int careUserId;
    private Date createTime;
}
