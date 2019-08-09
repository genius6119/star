package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 资讯模块服务提供者
 *
 * @author liufuwu
 * @date 2019-06-28 15:52
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NewsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsServiceApplication.class, args);
    }

}