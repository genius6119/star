package com.yc.service.impl;

import com.yc.dao.CommentsDao;
import com.yc.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liufuwu
 * @date 2019-07-03 11:12
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    @Override
    public void thumbsUpComments(int commentId) {
        commentsDao.thumbsUpComments(commentId);
    }

}