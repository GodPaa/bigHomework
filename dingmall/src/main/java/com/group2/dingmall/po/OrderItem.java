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
 * @since 2021-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderItem对象", description="")
public class OrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单关联购物项主键id")
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Long orderItemId;

    @ApiModelProperty(value = "订单主键id")
    private Long orderId;

    @ApiModelProperty(value = "关联商品id")
    private Long bookId;

    @ApiModelProperty(value = "下单时商品的名称(订单快照)")
    private String bookName;

    @ApiModelProperty(value = "下单时商品的主图(订单快照)")
    private String bookImg;

    @ApiModelProperty(value = "下单时商品的价格(订单快照)")
    private Integer price;

    @ApiModelProperty(value = "数量(订单快照)")
    private Integer bookCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
