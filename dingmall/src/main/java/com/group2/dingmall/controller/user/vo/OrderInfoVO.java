package com.group2.dingmall.controller.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/19 16:02
 * @Description
 *
 *     订单详情
 *      （确认订单界面使用）
 **/

@Data
public class OrderInfoVO {

    @ApiModelProperty("总价")
    private int totalPrice;


    @ApiModelProperty("购物项")
    private List<BookBriefVO> bookBriefVOList;


}
