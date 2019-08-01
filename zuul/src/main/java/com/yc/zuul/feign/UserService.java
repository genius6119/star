package com.yc.zuul.feign;

import com.yc.zuul.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-06-29 17:27
 **/
@FeignClient(value = "USER-SERVICE")
public interface UserService {

    @RequestMapping(value = "/user/findUserByMobile",method = RequestMethod.POST)
    User findUserByMobile(@RequestParam("mobile") String mobile);
}
