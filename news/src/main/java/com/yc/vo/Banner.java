package com.yc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liufuwu
 * @date 2019-07-09 15:32
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    private String title;
    private String content;
    private String imgUrl;
    private String userName;

}