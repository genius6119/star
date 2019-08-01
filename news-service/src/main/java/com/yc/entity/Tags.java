package com.yc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liufuwu
 * @date 2019-07-04 15:36
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tags {

    private Integer id;
    private String tagName;
}