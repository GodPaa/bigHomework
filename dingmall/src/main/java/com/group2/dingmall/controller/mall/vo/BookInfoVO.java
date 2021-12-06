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

    @ApiModelProperty("书本编号")
    private long id;
    @ApiModelProperty("书名")
    private String bookName;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("出版社")
    private String publishingHouse;
    @ApiModelProperty("书本标签")
    private String label;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("书本状态")
    private long bookStatus;
    @ApiModelProperty("译者")
    private String translator;
    @ApiModelProperty("出版年份")
    private String yearOfPublication;
    @ApiModelProperty("总页数")
    private long pages;
    @ApiModelProperty("ISBN")
    private String isbn;
    @ApiModelProperty("原价")
    private String originalPrice;
    @ApiModelProperty("秒杀价")
    private String currentPrice;
    @ApiModelProperty("叮叮评分")
    private double score;
    @ApiModelProperty("评价人数")
    private long numberOfPeople;
    @ApiModelProperty("内容简介")
    private String briefIntroduction;
    @ApiModelProperty("作者简介")
    private String authorIntroduction;
    @ApiModelProperty("目录")
    private String catalog;
}
