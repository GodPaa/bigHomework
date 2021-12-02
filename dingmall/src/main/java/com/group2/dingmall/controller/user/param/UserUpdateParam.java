package com.group2.dingmall.controller.user.param;

/**
 * @Author lv
 * @Date 2021/11/30 13:33
 * @Description
 **/


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改用户基本信息 param
 */
@Data

public class UserUpdateParam {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("个性签名")
    private String introduceSign;
}
