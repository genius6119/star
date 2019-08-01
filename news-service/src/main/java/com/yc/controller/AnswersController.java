package com.yc.controller;

import com.yc.common.ResponseData;
import com.yc.entity.Answers;
import com.yc.service.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 评论相关回复controller
 *
 * @author liufuwu
 * @date 2019-07-02 18:59
 */
@RestController
@RequestMapping(value = "/answers", method = RequestMethod.POST)
public class AnswersController {

    @Autowired
    private AnswersService answersService;

    @RequestMapping(value = "/queryAnswersListByCommentId")
    public ResponseData findAnswersByCommentId(int commentId, int pageNo, int pageSize) {
        List<Answers> answersList = answersService.queryAnswersListByCommentId(commentId, pageNo, pageSize);
        return ResponseData.ok().putKeyValue("answersList", answersList);
    }

    /**
     * 对评论的回复内容点赞
     *
     * @param answerId
     * @return
     */
    @Transactional
    @RequestMapping(value = "/thumbUpAnswer")
    public ResponseData thumbUpAnswerById(int answerId) {
        answersService.thumbsUpAnswers(answerId);
        return ResponseData.ok();
    }
}