package com.yc.userService.service;

import com.yc.userService.common.FeignMultiparConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-07-04 12:30
 **/
@FeignClient(value = "COMMON-SERVICE",configuration = FeignMultiparConfig.class)
public interface FeignService{

    @PostMapping (value = "/upload/fileUpload",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart(value = "file")MultipartFile file);
}
