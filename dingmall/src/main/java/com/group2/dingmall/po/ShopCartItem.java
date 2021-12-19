package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author lv
 * @since 2021-12-12
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShopCart对象", description="购物项,多个购物项组成购物车")
public class ShopCartItem implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "购物项主键id")
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Long cartItemId;

    @ApiModelProperty(value = "用户主键id")
    private Long userId;

    @ApiModelProperty(value = "关联商品id")
    private Long bookId;

    @ApiModelProperty(value = "数量")
    private Integer bookCount;


}
