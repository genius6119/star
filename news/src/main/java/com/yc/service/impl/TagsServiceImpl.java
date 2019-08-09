package com.yc.service.impl;

import com.yc.dao.TagsDao;
import com.yc.entity.Tags;
import com.yc.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-04 17:23
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsDao tagsDao ;

    @Override
    public List<Tags> findTagsList() {
        return tagsDao.queryTagsList();
    }
}