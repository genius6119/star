package com.yc.service;

import com.yc.entity.Tags;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-04 17:22
 */
public interface TagsService {

    /**
     * 查询默认标签
     */
    List<Tags> findTagsList();
}