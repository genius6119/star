package com.yc.service.impl;

import com.yc.dao.AnswersDao;
import com.yc.entity.Answers;
import com.yc.service.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-02 18:41
 */
@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswersDao answersDao;

    @Override
    public List<Answers> queryAnswersListByCommentId(int commentId, int pageNo, int pageSize) {
        return answersDao.queryCommentsListByNewsId(commentId, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public void thumbsUpAnswers(int answerId) {
        answersDao.thumbsUpAnswers(answerId);
    }
}