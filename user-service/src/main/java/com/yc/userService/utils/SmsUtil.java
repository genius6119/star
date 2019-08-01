package com.yc.userService.utils;

import com.yc.userService.constant.AccountConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * @author oak
 * @date 16/12/2018 22:27
 */
public class SmsUtil {

    private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    public static boolean send(String mobile, String content) {
        try {
            java.net.URL postUrl = new java.net.URL("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/http/sendBatchMessage");
            HttpURLConnection connection = (HttpURLConnection)postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String mobile_content = "account=" +
                    AccountConstant.SmsJianZhou.Account +
                    "&password=" +
                    AccountConstant.SmsJianZhou.Password +
                    "&sendDateTime=&destmobile="+mobile+"&msgText="+ URLEncoder.encode(content + "" +
                    "【赢客】", "UTF-8");
            out.writeBytes(mobile_content);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {

            }

            reader.close();
            connection.disconnect();
            return true;
        } catch (IOException e) {
            logger.error("手机号：{}，发送短信内容：{}发生异常：{}", mobile, content, e);
        }

        return false;
    }

}
