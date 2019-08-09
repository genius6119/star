package com.yc.userService.utils;
import java.util.Random;

/**
 * @program: star
 * @description: 随机生成用户名
 * @author: Zwx
 * @create: 2019-06-29 10:24
 **/
public class RandomNameUtils {

    /**
     * @param length
     * @return 生成随机用户名，数字和字母组成,
     */
    public static String getNameRandom(int length) {

        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
