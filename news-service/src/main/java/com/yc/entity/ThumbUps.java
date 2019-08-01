package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 点赞表
 *
 * @author liufuwu
 * @date 2019-07-06 16:25
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ThumbUps {

    private Integer id;

    private Integer newId;

    private Integer commentId;

    private Integer answerId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

}