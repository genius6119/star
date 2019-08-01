package com.yc.dao;

import com.yc.entity.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-02 10:58
 */
@Mapper
public interface ResourcesDao {

    @Select("select * from resources where `status` = 0 ")
    List<Resources> queryResourcesList();

    @Select("<script>" + "select * from resources " +
            "where `status` = 0 and  newsId in " +
            "<foreach item='item' index='index' collection='newsIdList' open='(' separator=',' close=')'> #{item}" +
            "</foreach> " +
            "</script>")
    List<Resources> queryResourcesByNewsList(@Param("newsIdList")List<Integer> newsIdList);

    @Select("select * from resources where `status` = 0 and newsId =#{newsId} ")
    List<Resources> queryResourcesByNewsId(@Param("newsId")Integer newsId);
}