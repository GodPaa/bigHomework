package com.group2.dingmall.controller.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author lv
 * @Date 2021/12/12 22:49
 * @Description
 **/
@Data
public class UserAddressVO {

    @ApiModelProperty(value = "用户主键id")
    private Long userId;

    @ApiModelProperty(value = "收货人姓名")
    private String userName;

    @ApiModelProperty(value = "收货人手机号")
    private String userPhone;

    @ApiModelProperty(value = "省")
    private String provinceName;

    @ApiModelProperty(value = "城")
    private String cityName;

    @ApiModelProperty(value = "区")
    private String regionName;

    @ApiModelProperty(value = "收件详细地址(街道/楼宇/单元)")
    private String detailAddress;

}
