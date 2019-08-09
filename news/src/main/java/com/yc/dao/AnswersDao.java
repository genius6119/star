package com.yc.dao;

import com.yc.entity.Answers;
import com.yc.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-02 18:00
 */
@Mapper
public interface AnswersDao {

    @Select("select count(*) from answers where `status` = 0 and commentId = #{commentId} ")
    Integer queryAnswersCountByCommentId(@Param("commentId") Integer commentId);


    @Select("select * from answers where `status` = 0 and commentId = #{commentId} order by createTime desc  limit #{pageNo},#{pageSize}  ")
    List<Answers> queryCommentsListByNewsId(@Param("commentId") Integer commentId,
                                            @Param("pageNo") int pageNo,
                                            @Param("pageSize") int pageSize);


    @Update("update answers set zanNums = zanNums + 1 where id = #{answerId}")
    void thumbsUpAnswers(@Param("answerId") Integer answerId);

}