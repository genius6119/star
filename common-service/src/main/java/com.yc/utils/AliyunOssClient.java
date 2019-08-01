package com.yc.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


/**
 * 阿里云OSS客户端
 *
 * @author liufw
 * @date 2019-05-24 11:20:00
 */
public class AliyunOssClient {

    /**
     * 访问OSS的域名
     */
    public static String ALIYUN_OSS_ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";

    public static String ALIYUN_OSS_ACCESSKEYID = "LTAIZM80U72idEMl";

    public static String ALIYUN_OSS_ACCESSKEYSECRET = "iL4IULMXqCs8mKEHXBpM5xUOczxFkR";

    /**
     * OSS存储空间
     */
    public static String ALIYUN_OSS_BACKETNAME = "sport-star-pic";

    /**
     * 图片服务器访问前缀
     */
    public static String ALIYUN_OSS_IMG_HOST = "http://sport-star-pic.oss-cn-hangzhou.aliyuncs.com";

    private static Logger logger = Logger.getLogger(AliyunOssClient.class);


    OSSClient ossClient = null;

    public AliyunOssClient() {
        ossClient = new OSSClient(AliyunOssClient.ALIYUN_OSS_ENDPOINT, AliyunOssClient.ALIYUN_OSS_ACCESSKEYID, AliyunOssClient.ALIYUN_OSS_ACCESSKEYSECRET);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(AliyunOssClient.ALIYUN_OSS_ENDPOINT, AliyunOssClient.ALIYUN_OSS_ACCESSKEYID, AliyunOssClient.ALIYUN_OSS_ACCESSKEYSECRET);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 创建存储空间
     *
     * @param bucketName
     */
    public void createBucket(String bucketName, CannedAccessControlList accessControlList) {

        // 新建存储空间默认为标准存储类型，私有权限。
        ossClient.createBucket(bucketName);

        // 设置存储空间的访问权限为私有。
        ossClient.setBucketAcl(bucketName, accessControlList);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /**
     * 删除存储空间
     *
     * @param bucketName
     */
    public void deleteBucket(String bucketName) {

        // 删除存储空间。
        ossClient.deleteBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /**
     * @param file
     * @return
     */
    public String putFile(File file, String fileName) {
        FileInputStream fileInputStream = null;
        try {
            String path = AliyunOssUtils.getDayFolder() + "/" + AliyunOssUtils.getFileUuidName() + AliyunOssUtils.getFileExt(fileName);

            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            fileInputStream = new FileInputStream(file);
            int length = fileInputStream.available();
            meta.setContentLength(length);
            meta.setCacheControl("no-cache");
            meta.setHeader("Pragma", "no-cache");
            meta.setContentType(AliyunOssUtils.getContentType(AliyunOssUtils.getFileExtStr(fileName)));
            meta.setContentDisposition("inline;filename=" + fileName);

            // 上传文件
            ossClient.putObject(ALIYUN_OSS_BACKETNAME, path, file, meta);

            String imgUrl = ALIYUN_OSS_IMG_HOST + "/" + path;
            logger.info("image url:" + imgUrl);
            return imgUrl;
        } catch (Exception e) {
            logger.error("OOS upload file error:", e);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
        return null;
    }


    public String uploadFile(String fileName, MultipartFile multipartFile) throws OSSException, ClientException, FileNotFoundException {

        StringBuffer sb = new StringBuffer();
        String path = AliyunOssUtils.getDayFolder() + "/" + AliyunOssUtils.getFileUuidName() + AliyunOssUtils.getFileExt(fileName);
        if (multipartFile.getSize() != 0 && !"".equals(multipartFile.getName())) {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(multipartFile.getSize());
            meta.addUserMetadata("filename", fileName);
            meta.setCacheControl("no-cache");
            meta.setHeader("Pragma", "no-cache");
            meta.setContentType(AliyunOssUtils.getContentType(AliyunOssUtils.getFileExtStr(fileName)));
            meta.setContentDisposition("inline;filename=" + fileName);
            try {
                ossClient.putObject(ALIYUN_OSS_BACKETNAME, path, new ByteArrayInputStream(multipartFile.getBytes()), meta);
            } catch (IOException e) {
                logger.error("OOS uploadFile MultipartFile error:", e);
            }
        }
        String imgUrl = ALIYUN_OSS_IMG_HOST + "/" + path;
        logger.info("image url:" + imgUrl);
        return imgUrl;
    }


    public static void main(String[] args) {
        AliyunOssClient ossClient = new AliyunOssClient();
        ossClient.createBucket(ALIYUN_OSS_BACKETNAME, CannedAccessControlList.PublicRead);
        //ossClient.deleteBucket(ALIYUN_OSS_BACKETNAME);
    }


}