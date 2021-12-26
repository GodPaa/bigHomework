package com.group2.dingmall.controller.admin.param;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)


public class BookParam {



    @ApiModelProperty(value = "书的id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "书名")
    private String bookName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "出版社")
    private String publishingHouse;

    @ApiModelProperty(value = "译者")
    private String translator;

    @ApiModelProperty(value = "出版时间")
    private String yearOfPublication;

    @ApiModelProperty(value = "页数")
    private Integer pages;

    @ApiModelProperty(value = "ISBN号码")
    @TableField("ISBN")
    private String isbn;

    @ApiModelProperty(value = "原价")
    private String originalPrice;

    @ApiModelProperty(value = "秒杀价")
    private String currentPrice;

    @ApiModelProperty(value = "书的评分")
    private Float score;

    @ApiModelProperty(value = "评价的人数")
    private Integer numberOfPeople;

    @ApiModelProperty(value = "内容简介")
    private String briefIntroduction;

    @ApiModelProperty(value = "作者简介")
    private String authorIntroduction;

    @ApiModelProperty(value = "目录")
    private String catalog;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "书的封面url地址")
    private String imgUrl;

    @ApiModelProperty(value = "商品上架状态 1-上架 0-下架")
    private Integer bookStatus;



}
