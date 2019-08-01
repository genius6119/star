package com.yc.controller;

import com.yc.utils.AliyunOssClient;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * 图片上传工具类
 *
 * @author liufuwu
 * @date 2019-07-03 14:19
 */
@RestController
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadImagesController {

    private static Logger logger = Logger.getLogger(UploadImagesController.class);

    @RequestMapping("fileUpload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        AliyunOssClient ossClient = new AliyunOssClient();
        String url = null;
        try {
            url = ossClient.uploadFile(file.getOriginalFilename(), file);
        } catch (FileNotFoundException e) {
            logger.error("UploadImagesController upload file fail:", e);
        }
        return url;
    }
}