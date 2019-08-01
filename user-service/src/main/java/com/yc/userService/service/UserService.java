package com.yc.userService.service;

import com.yc.userService.common.UserException;
import com.yc.userService.model.SimpleNews;
import com.yc.userService.model.SimpleUser;
import com.yc.userService.model.User;
import com.yc.userService.model.UserInfo;

import java.util.List;


/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-06-28 16:09
 **/
public interface UserService{

    void insertUser(User user);

    void sendVerifyCode(String mobile) throws UserException;

    String loginByVerifyCode(String mobile, String verifyCode) throws UserException;

    User findUserByMobile(String mobile);

    User findUserById(Integer Id);

    UserInfo selectUserInfo(String mobile);

    void changeUserInfo(String attribute, String value,String mobile);

    void logout(String token);

    List<SimpleUser> findMyAttention(String mobile,int pageNo,int pageSize);

    List<SimpleUser> findMyFans(String mobile,int pageNo,int pageSize);

    List<SimpleNews> findMyArticle(String mobile, int pageNo, int pageSize);
}
