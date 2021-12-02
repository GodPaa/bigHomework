package com.group2.dingmall.controller.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lv
 * @Date 2021/12/1 17:05
 * @Description
 **/
@Data
public class UserInfoVO {
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户登录名")
    private String loginName;

    @ApiModelProperty("个性签名")
    private String introduceSign;
}
