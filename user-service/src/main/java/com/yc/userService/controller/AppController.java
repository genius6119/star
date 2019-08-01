package com.yc.userService.controller;

import com.yc.userService.common.ResponseData;
import com.yc.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-07-03 10:18
 **/
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @RequestMapping("/environment")
    public ResponseData environment(String environment,
                                    @RequestParam("mobile") String mobile){
        userService.changeUserInfo("environment",environment,mobile);
        return ResponseData.ok();
    }
}
