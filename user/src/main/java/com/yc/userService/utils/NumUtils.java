package com.yc.userService.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-06-28 17:03
 **/
public class NumUtils {

    /**
     *  随机生成验证码
     * @return
     */
    public static String verCode(){
        Random random=new Random();
        /**取随机生成的数的第2位到第6位*/
        return StringUtils.substring(String.valueOf(random.nextInt()),2,6);
    }

    /**
     * 手机号正则验证
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        return isMatch;
    }

    /**
     * MD5 加密
     * @param source
     * @return
     */
    public static String getMD5(String source){
        return DigestUtils.md5Hex(source);
    }

}
