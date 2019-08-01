package com.yc.userService.service.impl;

import com.yc.userService.common.UserException;
import com.yc.userService.common.cache.RedisCacheUtil;
import com.yc.userService.constant.Constant;
import com.yc.userService.dao.UserMapper;
import com.yc.userService.model.*;
import com.yc.userService.service.UserService;
import com.yc.userService.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-06-28 16:09
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void sendVerifyCode(String mobile) throws UserException {

        Integer mobileSendTimes = 0;
        if(StringUtils.isNotEmpty((redisCacheUtil.getCacheValue(Constant.cacha_prefix.mobileSendTimes + mobile)))){
            mobileSendTimes = Integer.parseInt(redisCacheUtil.getCacheValue(Constant.cacha_prefix.mobileSendTimes + mobile));
        }

        if( mobileSendTimes >= 5 ){
            throw new UserException("验证码发送次数过多，请明天重试");
        }
        if(!NumUtils.isPhone(mobile)){
             throw new UserException("手机号格式错误");
        }
//        if(CacheUtils.cache.getIfPresent(Constant.cacha_prefix.verifyCode + mobile) != null){
//            throw new UserException("验证码还未过期");
//        }

        String verCode = NumUtils.verCode();
        redisCacheUtil.cacheNxExpire(Constant.cacha_prefix.verifyCode + mobile,verCode,5 * 60);
        redisCacheUtil.cacheNxExpire(Constant.cacha_prefix.mobileSendTimes + mobile, (++mobileSendTimes).toString(),24 * 60 * 60);
        String content = String.format("[体育星球]你的验证码是：%s ," +
                                        "此验证码用于登录验证，" +
                                        "请勿泄露给他人使用，5分钟内有效。",
                                        verCode);

        SmsUtil.send(mobile,content);
    }

    @Override
    public String loginByVerifyCode(String mobile, String verifyCode) throws UserException{

        String cacheVerifyCode = redisCacheUtil.getCacheValue(Constant.cacha_prefix.verifyCode + mobile);
        if (StringUtils.isEmpty(cacheVerifyCode) ||
                (StringUtils.isNotEmpty(cacheVerifyCode) && !cacheVerifyCode.equals(verifyCode))){
            throw new UserException("验证码错误，请重新输入");
        }

        User user = userMapper.selectUserByMobile(mobile);

        if(user == null){
            String ranName = RandomNameUtils.getNameRandom(11);
            userMapper.insertUser(new User(ranName,mobile,new Date()));
        }else {
            userMapper.updateLastLoginTime(new Date(),user.getID());
        }

        String token = NumUtils.getMD5(user.getMobile() + System.currentTimeMillis());
        String lastToken = redisCacheUtil.getCacheValue(Constant.cacha_prefix.mobileHistoryToken);

        if(StringUtils.isNotEmpty(lastToken)){
            redisCacheUtil.delKey(lastToken);
        }
        redisCacheUtil.cacheNxExpire(token,mobile,60 * 60 * 24 * 30);
        redisCacheUtil.cacheNxExpire(Constant.cacha_prefix.mobileHistoryToken,token,60 * 60 * 24 * 30);

        return token;
    }

    @Override
    public User findUserByMobile(String mobile) {
        return userMapper.selectUserByMobile(mobile);
    }

    @Override
    public User findUserById(Integer Id) {
        return userMapper.selectUserById(Id);
    }

    @Override
    public UserInfo selectUserInfo(String mobile) {
        UserInfo userInfo = new UserInfo();
        User user = userMapper.selectUserByMobile(mobile);
        userInfo.setUserName(user.getUserName());
        userInfo.setSignature(user.getSignature());
        userInfo.setAvatar(user.getAvatar());
        /**
         *  先写死
         */
        userInfo.setAttentionNum(100);
        userInfo.setFansNum(1111);
        userInfo.setArticleNum(1234);
        userInfo.setCircleNum(75);
        /**
         * 设置小红点
         */
        userInfo.setCouponsFlag(1);

        return userInfo;
    }

    @Override
    public void changeUserInfo(String attribute, String value,String mobile) {
        userMapper.updateAttribute(attribute,value,mobile);
    }

    @Override
    public void logout(String token) {
        redisCacheUtil.delKey(token);
    }

    @Override
    public List<SimpleUser> findMyAttention(String mobile,int pageNo,int pageSize) {

        User user = userMapper.selectUserByMobile(mobile);
        List<SimpleUser> attentionUsers = new ArrayList<>();
        if(user != null){
            List<UserCare> userCares = userMapper.selectCareUserById(user.getID(),(pageNo - 1) * pageSize, pageSize);
            for (UserCare u : userCares){
                User careUser = userMapper.selectUserById(u.getCareUserId());
                attentionUsers.add(new SimpleUser(careUser.getUserName(),careUser.getSignature(),careUser.getAvatar()));
            }
        }
        return attentionUsers;
    }

    @Override
    public List<SimpleUser> findMyFans(String mobile,int pageNo,int pageSize) {
        User user = userMapper.selectUserByMobile(mobile);
        List<SimpleUser> fansUsers = new ArrayList<>();
        if (user != null) {
            List<UserCare> userCares = userMapper.selectCareUserBycId(user.getID(),(pageNo - 1) * pageSize, pageSize);
            for (UserCare u : userCares) {
                User fansUser = userMapper.selectUserById(u.getUserId());
                fansUsers.add(new SimpleUser(fansUser.getUserName(), fansUser.getSignature(), fansUser.getAvatar()));
            }
        }
        return fansUsers;
    }

    @Override
    public List<SimpleNews> findMyArticle(String mobile, int pageNo, int pageSize) {
        User user = userMapper.selectUserByMobile(mobile);
        List<SimpleNews> simpleNews = null;
        if (user != null){
            simpleNews = userMapper.selectArticleByUserId(user.getID(),(pageNo -1) * pageSize,pageSize);
        }
        return simpleNews;
    }

}
