package com.yc.feign;

import com.yc.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liufuwu
 * @date 2019-07-04 14:43
 */
@FeignClient("user-service")
public interface UserFeignClient {

    @RequestMapping(value = "/user/findUserById", method = RequestMethod.POST)
    public User findUserById(@RequestParam("id") Integer id);

    @RequestMapping(value = "/user/findUserByMobile", method = RequestMethod.POST)
    public User findUserByMobile(@RequestParam("mobile") String mobile);
}