package com.yc.utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 阿里云OSS工具类
 *
 * @author liufw
 * @date 2019-05-24 11:20:00
 */
public class AliyunOssUtils {

    static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 判断上传文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        } else if (FilenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        } else if (FilenameExtension.equalsIgnoreCase("jpeg") || FilenameExtension.equalsIgnoreCase("jpg")
                || FilenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        } else if (FilenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        } else if (FilenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        } else if (FilenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        } else if (FilenameExtension.equalsIgnoreCase("pptx") || FilenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        } else if (FilenameExtension.equalsIgnoreCase("docx") || FilenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        } else if (FilenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        } else if (FilenameExtension.equalsIgnoreCase("mp3")) {
            return "audio/mp3";
        } else if (FilenameExtension.equalsIgnoreCase("xls")) {
            return "application/x-xls";
        } else if (FilenameExtension.equalsIgnoreCase("csv")) {
            return "text/csv";
        } else if (FilenameExtension.equalsIgnoreCase("mp4")) {
            return "audio/mp4";
        }
        return "application/octet-stream";
    }

    /**
     * 获取文件后缀（包含.）
     *
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileExtStr(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取日期文件夹名称
     *
     * @return
     */
    public static String getDayFolder() {
        return datetimeFormat.format(System.currentTimeMillis());
    }

    /**
     * 随机UUID
     *
     * @return
     */
    public static String getFileUuidName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
