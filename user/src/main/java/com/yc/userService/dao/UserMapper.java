package com.yc.userService.dao;

import com.yc.userService.model.SimpleNews;
import com.yc.userService.model.User;
import com.yc.userService.model.UserCare;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @program: star
 * @description: userMapper
 * @author: Zwx
 * @create: 2019-06-28 16:01
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user(userName, mobile, registerTime,lastLoginTime) VALUES (#{userName}, #{mobile}, #{registerTime}, #{lastLoginTime})")
    void insertUser(User user);

    @Select("select * from user where mobile = #{mobile}")
    User selectUserByMobile(String mobile);

    @Update("update user set lastLoginTime = #{date} where id = #{id}")
    void updateLastLoginTime(@Param("date") Date date, @Param("id") Integer id);

    @Update("update user set ${attribute} = #{value} where mobile = #{mobile}")
    void updateAttribute(@Param("attribute") String attribute, @Param("value") String value, @Param("mobile") String mobile);

    @Select("select * from user where ID = #{id}")
    User selectUserById(@Param("id") Integer id);

    @Select("select * from user_care where userId = #{userId} limit #{start},#{end}")
    List<UserCare> selectCareUserById(@Param("userId") int userId,@Param("start") int start,@Param("end") int end);

    @Select("select * from user_care where careUserId = #{careUserId} limit #{start},#{end}")
    List<UserCare> selectCareUserBycId(@Param("careUserId")int careUserId,@Param("start") int start,@Param("end") int end);

    @Select("select * from news where userId = #{userId} limit #{start},#{end} ")
    List<SimpleNews> selectArticleByUserId(@Param("userId") int userId,@Param("start") int start, @Param("end") int pageSize);
}
