package com.yc.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.ResponseData;
import com.yc.entity.Tags;
import com.yc.entity.User;
import com.yc.entity.UserTags;
import com.yc.feign.UserFeignClient;
import com.yc.service.NewsService;
import com.yc.service.TagsService;
import com.yc.vo.UserInfoVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯标签controller
 *
 * @author liufuwu
 * @date 2019-07-04 14:30
 */
@RestController
@RequestMapping(value = "/tags", method = RequestMethod.POST)
public class TagsController {

    private static Logger logger = Logger.getLogger(TagsController.class);

    @Autowired
    private UserFeignClient userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private TagsService tagsService;


    /**
     * 查询用户登录后默认显示的标签
     *
     * @return
     */
    @RequestMapping("findtagsList")
    public ResponseData findDefaultTags() {
        List<Tags> tagsList = tagsService.findTagsList();
        return ResponseData.ok().putKeyValue("tagsList",tagsList);
    }

    /**
     * 绑定用户标签
     */
    @RequestMapping("bindUserTags")
    public ResponseData bindUserTags(@RequestBody UserInfoVO userInfo) {

        if (userInfo != null) {
            User user = userService.findUserById(userInfo.getUserId());
            if (user == null) {
                return ResponseData.error("该用户不存在");
            }
            List<Integer> tagIdList = userInfo.getTagIdList();
            if (CollectionUtils.isEmpty(tagIdList)) {
                return ResponseData.error("用户标签不能为空，请重新选择用户标签");
            } else {
                for (Integer tagId : tagIdList) {
                    UserTags userTags = new UserTags();
                    userTags.setUserId(userInfo.getUserId());
                    userTags.setTagId(tagId);
                    newsService.inserUserTags(userTags);
                }
            }
        }
        return ResponseData.ok();
    }

   /* public static void main(String[] args) {
        UserInfoVO info = new UserInfoVO();
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        info.setUserId(1);
        info.setTagIdList(idList);

        System.out.println(JSONObject.toJSONString(info));

    }*/
}