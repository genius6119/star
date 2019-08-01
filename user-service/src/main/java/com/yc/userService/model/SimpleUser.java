package com.yc.userService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: star
 * @description: 精简的用户类
 * @author: Zwx
 * @create: 2019-07-06 15:48
 **/
@Data
@AllArgsConstructor
public class SimpleUser implements Serializable {
    private String userName;
    private String signature;
    private String avatar;
}
