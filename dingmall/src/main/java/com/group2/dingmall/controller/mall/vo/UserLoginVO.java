package com.group2.dingmall.controller.mall.vo;



/**
 * @Author lv
 * @Date 2021/11/11 16:27
 * @Description   登录成功后给AJAX返回用户对象，但 User 有很多字段，而我们只需要其中几个
 *                这种情况我们可以使用一个封装类
 **/

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录param
 */
@Data
public class UserLoginVO implements Serializable {

    @ApiModelProperty("token")
    private String token;



}
