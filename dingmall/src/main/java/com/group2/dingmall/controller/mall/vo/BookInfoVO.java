package com.group2.dingmall.controller.mall.vo;

/**
 * @Author lv
 * @Date 2021/12/2 11:08
 * @Description
 **/

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * book详情页VO
 */
@Data
public class BookInfoVO implements Serializable {

    @ApiModelProperty("书本id")
    private Long bookId;

    @ApiModelProperty("书本名称")
    private String bookName;

    @ApiModelProperty("书本类别")
    private String bookType;

    @ApiModelProperty("书本作者")
    private String bookAuthor;

    @ApiModelProperty("书本存量")
    private long bookStock;

    @ApiModelProperty("书本价格")
    private long bookPrice;

    @ApiModelProperty("书本简介")
    private String bookIntro;

    @ApiModelProperty("书本图片地址")
    private String bookImgUrl;






}
