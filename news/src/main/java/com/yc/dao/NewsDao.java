package com.yc.dao;


import com.yc.entity.News;
import com.yc.entity.UserTags;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liufw
 * @date 2019/06/28
 */
@Mapper
public interface NewsDao {

    @Select("select news.id,news.title,news.content,`user`.userName AS author,news.createTime from news \n" +
            "left join `user` on news.userId = `user`.ID\n" +
            "left join  users_tags on users_tags.userId = `user`.ID\n" +
            "left join  tags on tags.id = users_tags.tagId\n" +
            "where news.`status` = 0 and news.top = 0 " +
            "and `user`.ID = #{userId} order by createTime desc limit #{pageNo},#{pageSize} ")
    List<News> queryRecommandNewsList(@Param("userId") int userId,
                                      @Param("pageNo") int pageNo,
                                      @Param("pageSize") int pageSize);

    @Select("select * from news where id=#{id}")
    News queryNewsById(@Param("id") Integer id);

    @Insert("insert into users_tags (userId,tagId) values(#{userId},#{tagId})")
    void inserUserTags(UserTags userTags);

    @Select("select * from news where `status` = 0 and top = 1 " )
    News queryBannerNews();
}
