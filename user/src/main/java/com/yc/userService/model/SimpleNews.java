package com.yc.userService.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * @program: star
 * @description: 精简的资讯实体类
 * @author: Zwx
 * @create: 2019-07-06 17:14
 **/
@Data
public class SimpleNews implements Serializable {
    private String title;
    private int zanNums;
    private int scanNums;
    private int commentNums;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
