package com.group2.dingmall.controller.mall.vo;

/**
 * @Author lv
 * @Date 2021/12/12 20:57
 * @Description
 **/


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 购物车页面需要的数据
 */
@Data
public class BookInCartVO {
    @ApiModelProperty("购物项Id(用来生成订单)")
    private long cartId;
    @ApiModelProperty("bookId(用来请求书本详细数据，比如购物车页面点击商品查看详情)")
    private long bookId;
    @ApiModelProperty("购买数量(用来生成订单)")
    private long bookCount;
    @ApiModelProperty("书名")
    private String bookName;
    @ApiModelProperty("出版社")
    private String publishingHouse;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("价格")
    private String originalPrice;
    @ApiModelProperty("简介")
    private String briefIntroduction;
    @ApiModelProperty("图片地址")
    private String imgUrl;
}
