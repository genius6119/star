package com.yc.userService.controller;

import com.yc.userService.common.UserException;
import com.yc.userService.model.SimpleNews;
import com.yc.userService.model.SimpleUser;
import com.yc.userService.model.User;
import com.yc.userService.model.UserInfo;
import com.yc.userService.service.FeignService;
import com.yc.userService.service.UserService;
import com.yc.userService.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: star
 * @description: 用户Controller
 * @author: Zwx
 * @create: 2019-06-28 14:39
 **/
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FeignService feignService;

    @RequestMapping("/login/verifyCode")
    public ResponseData login(String mobile,
                              String verifyCode){
        try {
            String token = userService.loginByVerifyCode(mobile,verifyCode);
            return ResponseData.ok().putKeyValue("token",token);
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }
    }

    @RequestMapping("/logout")
    public ResponseData logout(@RequestHeader String token){
        userService.logout(token);
        return ResponseData.ok();
    }

    @RequestMapping("/sendVerifyCode")
    public ResponseData sendVerifyCode(String mobile){
        try {
            userService.sendVerifyCode(mobile);
        } catch (UserException e) {
            return ResponseData.error(e.getMessage());
        }
        return ResponseData.ok("验证码已发送");
    }

    @RequestMapping("/findUserByMobile")
    public User findUserByMobile(@RequestParam("mobile") String mobile){
        return userService.findUserByMobile(mobile);
    }

    @RequestMapping("/findUserById")
    public User findUserByMobile(@RequestParam("id") Integer id){
        return userService.findUserById(id);
    }

    @RequestMapping("/info")
    public ResponseData uerInfo(@RequestParam("mobile") String mobile){
        UserInfo userInfo = userService.selectUserInfo(mobile);
        return ResponseData.ok().putKeyValue("userInfo",userInfo);
    }

    @RequestMapping("/changeUserInfo")
    public ResponseData changeUserInfo(String attribute,
                                       String value,
                                       @RequestParam("mobile") String mobile) {
        userService.changeUserInfo(attribute, value,mobile);
        return ResponseData.ok();
    }

    @RequestMapping("/uploadHeadImg")
    public ResponseData uploadHeadImg(MultipartFile file,
                                      @RequestParam("mobile") String mobile){

        String url = feignService.uploadFile(file);
        if (StringUtils.isNotEmpty(url)) {
            userService.changeUserInfo("avatar", url, mobile);
        }

        return ResponseData.ok("上传头像成功");
    }

    @RequestMapping("/my/attention")
    public ResponseData myAttention(@RequestParam("mobile") String mobile,
                                    @RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(defaultValue = "20") int pageSize){
        List<SimpleUser> attentionUsers = userService.findMyAttention(mobile,pageNo,pageSize);
        return ResponseData.ok().putKeyValue("myAttention",attentionUsers);
    }

    @RequestMapping("/my/fans")
    public ResponseData myFans(@RequestParam("mobile") String mobile,
                               @RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "20") int pageSize){
        List<SimpleUser> fansUsers = userService.findMyFans(mobile,pageNo,pageSize);
        return ResponseData.ok().putKeyValue("myFans",fansUsers);
    }

    @RequestMapping("/my/article")
    public ResponseData myArticle(@RequestParam("mobile") String mobile,
                                  @RequestParam(defaultValue = "1") int pageNo,
                                  @RequestParam(defaultValue = "20") int pageSize){
        List<SimpleNews> simpleNews = userService.findMyArticle(mobile,pageNo,pageSize);
        return ResponseData.ok().putKeyValue("news",simpleNews);
    }

    @RequestMapping("/my/circle")
    public ResponseData myCircle(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/energy")
    public ResponseData myEnergy(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/wallet")
    public ResponseData myWallet(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/coupons")
    public ResponseData myCoupons(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/collection")
    public ResponseData myCollection(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/answers")
    public ResponseData myAnswers(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/like")
    public ResponseData myLike(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/history")
    public ResponseData myHistory(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/scheme")
    public ResponseData myScheme(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }

    @RequestMapping("/my/invite")
    public ResponseData myInvite(@RequestParam("mobile") String mobile){
        return ResponseData.ok();
    }
}
