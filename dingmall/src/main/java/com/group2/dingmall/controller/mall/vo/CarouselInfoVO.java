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
 * 轮播图数据
 */
@Data
public class CarouselInfoVO implements Serializable {

    @ApiModelProperty("书本编号")
    private long id;
    @ApiModelProperty("书名")
    private String bookName;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("图片地址")
    private String imgUrl;

}
