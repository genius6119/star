package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author liufuwu
 * @date 2019-07-04 15:58
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTags {

    private Integer userId;
    private Integer tagId;
    private Date createTime;
    private Date updateTime;

}