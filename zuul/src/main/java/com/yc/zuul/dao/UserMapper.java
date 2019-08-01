package com.yc.zuul.dao;

import com.yc.zuul.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: star
 * @description:
 * @author: Zwx
 * @create: 2019-07-06 11:37
 **/
@Mapper
public interface UserMapper {
    @Select("select * from user where mobile = #{mobile}")
    User selectUserByMobile(String mobile);
}
