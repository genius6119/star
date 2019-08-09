package com.yc.dao;

import com.yc.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 评论Dao
 *
 * @author liufuwu
 * @date 2019-07-02 14:45
 */
@Mapper
public interface CommentsDao {


    @Select("select count(*) from comments where `status` = 0 and newsId = #{newsId} ")
    Integer queryCommentsCountByNewsId(@Param("newsId") Integer newsId);

    @Select("select * from comments where `status` = 0 and newsId = #{newsId} order by createTime desc  limit #{pageNo},#{pageSize}  ")
    List<Comments> queryCommentsListByNewsId(@Param("newsId") Integer newsId,
                                             @Param("pageNo") int pageNo,
                                             @Param("pageSize") int pageSize);

    @Update("update comments set zanNums = zanNums + 1 where id = #{commentId}")
    void thumbsUpComments(int commentId);
}
