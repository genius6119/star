package com.yc.userService.common;

import com.yc.userService.constant.Constant;

/**
 * @program: star
 * @description: 用户模块的异常
 * @author: Zwx
 * @create: 2019-06-28 17:41
 **/
public class UserException extends Exception {

    public UserException(String message) { super(message); }

    public int getStatusCode(){
        return Constant.status.error;
    }
}
