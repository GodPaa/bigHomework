package com.group2.dingmall.controller.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author lv
 * @Date 2021/12/12 23:07
 * @Description  用户地址添加表单
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAddressParam {

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
