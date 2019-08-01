package com.yc.zuul.constant;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-06-29 16:17
 **/
public interface Constant {

    interface UnLoginInterface{
        String hello = "/user-service/hello";
        String sendVerifyCode = "/user-service/user/sendVerifyCode";
        String loginByVerifyCode = "/user-service/user/login/verifyCode";
    }
}
