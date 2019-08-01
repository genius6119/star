package com.yc.service;

import com.yc.entity.News;
import com.yc.entity.UserTags;
import com.yc.vo.Banner;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-02 18:42
 */
public interface NewsService {

    /**
     * 资讯列表--推荐(分页)
     *
     * @return
     */
    public List<News> queryRecommandNewsList(int userId, int pageNo, int pageSize);


    /**
     * 根据Id查询资讯信息
     *
     * @param id
     * @return
     */
    public News queryNewsById(int id, int pageNo, int pageSize);

    /**
     * 绑定用户标签
     *
     * @param userTags
     */
    public void inserUserTags(UserTags userTags);

    /**
     * 查询Banner图
     *
     * @return
     */
    Banner queryBannerNews();
}