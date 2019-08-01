package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

/**
 * 资讯
 * Created by liufw on 2019/06/28.
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private Integer id;
    private String title;
    private String content;
    /**
     * 是否置顶
     * 1 置顶
     * 0 不置顶
     */
    private Integer top;
    private Integer hot;
    private Integer sort;
    /**
     * 资源类型
     * 1 无图无视频
     * 2 单张大图
     * 3 三张大图
     * 4 视频专题
     */
    private Integer resourceType;
    private Integer status;
    private Integer recommand;
    private Date createTime;
    private Date updateTime;

    private String author;
    private Integer zanNums;
    private Integer scanNums;
    private Integer commentNums;
    private List<Resources> resourcesList;
    private List<Comments> commentsList;

}