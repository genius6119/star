package com.yc.controller;

import com.yc.common.cache.RedisCacheUtil;
import com.yc.feign.UserFeignClient;
import com.yc.vo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufuwu
 * @date 2019-07-09 17:12
 */
@RestController
@RequestMapping(value = "/common", method = RequestMethod.POST)
public class TokenUtilController {

    @Autowired
    private UserFeignClient userService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 获取用户登录Id
     *
     * @return
     */
    @RequestMapping("/getLoginUser")
    public User getLoginUser(@RequestParam("mobile") String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            User user = userService.findUserByMobile(mobile);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

}