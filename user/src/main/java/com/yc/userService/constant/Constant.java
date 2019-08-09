package com.yc.userService.constant;

/**
 * @author oak
 * @date 23/11/2018 19:09
 */
public interface Constant {

    interface status{
        int error = 500;
    }

    interface cacha_prefix{
        String verifyCode = "verifyCode_";
        String mobileSendTimes = "mobileSendTimes_";
        String mobileHistoryToken = "history_token_";
    }
}
