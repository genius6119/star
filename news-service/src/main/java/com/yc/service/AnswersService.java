package com.yc.service;

import com.yc.entity.Answers;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-02 18:56
 */
public interface AnswersService {

    /**
     * 评论相关回复列表(分页查询)
     *
     * @return
     */
    public List<Answers> queryAnswersListByCommentId(int commentId, int pageNo, int pageSize);

    void thumbsUpAnswers(int answerId);
}