package com.yc.feign;

import com.yc.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liufuwu
 * @date 2019-07-09 19:43
 */
@FeignClient("common-service")
public interface CommonFeignClient {

    @RequestMapping(value = "/common/getLoginUser", method = RequestMethod.POST)
    public User getLoginUserId(@RequestParam("mobile") String mobile);

}