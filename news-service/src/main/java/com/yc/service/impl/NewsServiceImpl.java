package com.yc.service.impl;

import com.yc.constant.Constant;
import com.yc.dao.AnswersDao;
import com.yc.dao.CommentsDao;
import com.yc.dao.NewsDao;
import com.yc.dao.ResourcesDao;
import com.yc.entity.Comments;
import com.yc.entity.News;
import com.yc.entity.Resources;
import com.yc.entity.UserTags;
import com.yc.service.NewsService;
import com.yc.vo.Banner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private ResourcesDao resourcesDao;

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private AnswersDao answersDao;

    /**
     * 资讯列表--推荐(分页)
     *
     * @return
     */
    @Override
    public List<News> queryRecommandNewsList(int userId,int pageNo, int pageSize) {
        List<News> newsList = newsDao.queryRecommandNewsList(userId,(pageNo - 1) * pageSize, pageSize);
        if (CollectionUtils.isEmpty(newsList)) {
            return null;
        }
        for (News news : newsList) {
            if (news.getId() != null) {
                List<Resources> resourcesList = resourcesDao.queryResourcesByNewsId(news.getId());
                Integer counts = commentsDao.queryCommentsCountByNewsId(news.getId());
                if (!CollectionUtils.isEmpty(resourcesList)) {
                    news.setResourcesList(resourcesList);
                    if (resourcesList.size() >= 3 && resourcesList.get(0).getType() == 1) {
                        //图片类型，且图片数量>= 3
                        news.setResourceType(3);
                    } else if (resourcesList.size() < 3 && resourcesList.get(0).getType() == 1) {
                        news.setResourceType(2);
                    } else if (resourcesList.get(0).getType() == 2) {
                        //视频类型
                        news.setResourceType(4);
                    }
                } else {
                    //无图无视频
                    news.setResourceType(1);
                }
                news.setCommentNums(counts);
            }
        }
        return newsList;
    }

    /**
     * 根据Id查询资讯信息
     *
     * @param id
     * @return
     */
    @Override
    public News queryNewsById(int id, int pageNo, int pageSize) {
        News news = newsDao.queryNewsById(id);
        List<Comments> commentsList = commentsDao.queryCommentsListByNewsId(id, (pageNo - 1) * pageSize, pageSize);
        for (Comments comments : commentsList) {
            Integer answerCount = answersDao.queryAnswersCountByCommentId(comments.getId());
            comments.setAnswerNums(answerCount);
        }
        news.setCommentsList(commentsList);

        return news;
    }

    @Override
    public void inserUserTags(UserTags userTags) {
        newsDao.inserUserTags(userTags);
    }

    @Override
    public Banner queryBannerNews() {
        News news =  newsDao.queryBannerNews();
        Banner banner = new Banner();
        if (news != null) {
            List<Resources> resourcesList = resourcesDao.queryResourcesByNewsId(news.getId());
            BeanUtils.copyProperties(news, banner);
            if(!CollectionUtils.isEmpty(resourcesList)){
                banner.setImgUrl(resourcesList.get(0).getUrl());
            }
        }
        return banner;
    }


}