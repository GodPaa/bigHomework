package com.group2.dingmall.controller.mall.param;

/**
 * @Author lv
 * @Date 2021/12/12 16:10
 * @Description
 **/


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 购物车的参数
 *      购物车某个购物项的参数（book_id,num）
 */
@Data
public class CartItemParam {

    @ApiModelProperty("book_id")
    private long bookId;

    @ApiModelProperty("book数量")
    private int bookCount;

}
