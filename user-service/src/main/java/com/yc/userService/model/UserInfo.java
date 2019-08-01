package com.yc.userService.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: star
 * @description: 【我的】用户信息
 * @author: Zwx
 * @create: 2019-07-02 10:40
 **/
@Data
public class UserInfo implements Serializable {
    private String userName;
    private String signature;
    private String avatar;
    private Integer attentionNum;
    private Integer fansNum;
    private Integer articleNum;
    private Integer circleNum;

    /**
     * 我的页面小红点的Flag  默认0：不显示小红点 1：显示小红点
     */
    private int systemNewsFlag;
    private int optionFlag;
    private int energyFlag;
    private int walletFlag;
    private int couponsFlag;
}
