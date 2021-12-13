package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserOrder对象", description="")
public class UserOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户主键id")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    @ApiModelProperty(value = "订单总价")
    private Integer totalPrice;

    @ApiModelProperty(value = "支付状态:0.未支付,1.支付成功")
    private Integer payStatus;

    @ApiModelProperty(value = "0.无 1.支付宝支付 2.微信支付")
    private Integer payType;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;


}
