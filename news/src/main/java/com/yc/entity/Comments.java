package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * 资讯相关评论
 *
 * @author liufuwu
 * @date 2019-07-02 14:43
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    private Integer id;

    private Integer newsId;

    private String content;

    private Integer zanNums;

    private Integer answerNums;

    private Date createTime;


}