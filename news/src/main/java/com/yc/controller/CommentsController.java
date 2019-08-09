package com.yc.controller;

import com.yc.common.ResponseData;
import com.yc.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资讯相关评论controller
 *
 * @author liufuwu
 * @date 2019-07-03 11:09
 */
@RestController
@RequestMapping(value = "/comments", method = RequestMethod.POST)
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 对评论点赞
     *
     * @param commentId
     * @return
     */
    @Transactional
    @RequestMapping(value = "/thumbUpComment")
    public ResponseData thumbUpCommentById(int commentId) {
        commentsService.thumbsUpComments(commentId);
        return ResponseData.ok();
    }
}