package com.yc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author liufuwu
 * @date 2019-07-04 15:35
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    private Integer userId;
    private List<Integer> tagIdList;

}