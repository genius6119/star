package com.yc.userService.common;

import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @program: star
 * @description: 使feign支持远程传输文件
 * @author: Zwx
 * @create: 2019-07-04 15:38
 **/
@Configuration
public class FeignMultiparConfig {
    @Bean
    @Primary
    @Scope("prototype")
    public SpringFormEncoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }

}
