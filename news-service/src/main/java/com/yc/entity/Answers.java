package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liufuwu
 * @date 2019-07-02 17:58
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Answers {

    private Integer id;

    private Integer newsId;

    private Integer contentId;

    private String content;

    private Integer userId;

    private Integer zanNums;

    private String userName;


}