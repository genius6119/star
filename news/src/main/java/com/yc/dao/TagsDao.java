package com.yc.dao;

import com.yc.entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-04 17:24
 */
@Mapper
public interface TagsDao {

    @Select("select * from tags where `status` = 0 ")
    public List<Tags> queryTagsList();
}