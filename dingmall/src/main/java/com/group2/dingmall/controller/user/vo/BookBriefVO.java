package com.group2.dingmall.controller.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lv
 * @Date 2021/12/19 16:22
 * @Description
 **/
@Data
public class BookBriefVO {

    @ApiModelProperty("bookId")
    private long bookId;
    @ApiModelProperty("书名")
    private String bookName;
    @ApiModelProperty("图片地址")
    private String bookImg;
    @ApiModelProperty("价格")
    private int price;
    @ApiModelProperty("购买数量")
    private int bookCount;
}
