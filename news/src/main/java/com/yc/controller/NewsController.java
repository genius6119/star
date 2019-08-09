package com.yc.controller;

import com.yc.common.ResponseData;
import com.yc.entity.News;
import com.yc.entity.User;
import com.yc.feign.CommonFeignClient;
import com.yc.feign.UserFeignClient;
import com.yc.service.impl.NewsServiceImpl;
import com.yc.vo.Banner;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资讯模块生产者controller
 *
 * @author liufuwu
 * @date 2019-06-28 17:13
 */
@RestController
@RequestMapping(value = "/news", method = RequestMethod.POST)
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    @Autowired
    private UserFeignClient userService;

    @Autowired
    private CommonFeignClient tokenClient;


    /**
     * 资讯列表--推荐
     *
     * @return
     */
    @RequestMapping(value = "/queryRecommandNewsList")
    public ResponseData findRecommandNewsList(@RequestParam(defaultValue = "1") int pageNo,
                                              @RequestParam(defaultValue = "20") int pageSize,
                                              @RequestParam("mobile") String mobile) {

        User user = tokenClient.getLoginUserId(mobile);
        Banner bannerNews = newsService.queryBannerNews();
        if(bannerNews != null){
            bannerNews.setUserName(user.getUserName());
        }

        Map<String, Object> result = new HashMap<>();
        List<News> recommandList = newsService.queryRecommandNewsList(user.getID(), pageNo, pageSize);
        if (CollectionUtils.isEmpty(recommandList)) {
            result.put("banner", bannerNews);
            result.put("newsCount", 0);
            result.put("newsList", recommandList);
        } else {
            result.put("banner", bannerNews);
            result.put("newsCount", recommandList.size());
            result.put("newsList", recommandList);
        }
        return ResponseData.ok().putMap(result);
    }

    /**
     * 根据资讯编号查询资讯详细信息
     * 资讯相关的评论信息分页查询
     *
     * @param id
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryNewsInfoById")
    public ResponseData findNewsById(int id, int pageNo, int pageSize) {
        News news = newsService.queryNewsById(id, pageNo, pageSize);
        return ResponseData.ok().putKeyValue("news", news);
    }


   /* *//**
     * 资讯点赞功能
     * @param newsId
     * @return
     *//*
    @RequestMapping(value = "/thumbUpNews")
    public ResponseData thumbUpNewsById(int newsId) {
        newsService.thumbsUpComments(newsId);
        return ResponseData.ok()
    }*/


}