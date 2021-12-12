package com.group2.dingmall.controller.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户登录param
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserLoginParam implements Serializable {

    @ApiModelProperty("登录名")
    @NotNull(message = "登录名不能为空")
    private String loginName;

    @ApiModelProperty("用户密码(不需要MD5加密)")
    @NotNull(message = "密码不能为空")
    private String password;
}
