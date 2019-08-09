package com.yc.userService.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @program: star
 * @description: 缓存工具类
 * @author: Zwx
 * @create: 2019-06-28 18:08
 **/
public class CacheUtils {

    // 用户校验手机号验证码的缓存 有效时间5分钟
    public static Cache<String,String> cache = CacheBuilder.newBuilder().expireAfterWrite( 60 * 5, TimeUnit.SECONDS).build();

    // 用户校验手机号验证码一天发送次数的缓存 有效时间24小时
    public static Cache<String,String> mobileSendTimesCache = CacheBuilder.newBuilder().expireAfterWrite(24 * 60 * 60, TimeUnit.SECONDS).build();

}
