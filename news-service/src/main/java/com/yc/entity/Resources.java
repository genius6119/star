package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * 图片
 *
 * @author liufuwu
 * @date 2019-06-29 11:47
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resources {

    private Integer id;
    private String url;
    private Integer status;
    /**
     * 资源类型
     * 1 图片
     * 2 视频
     */
    private Integer type;
    private String createUser;
    private Date createTime;
    private Date updateTime;

    private String author;
    private Integer zanNums;
}