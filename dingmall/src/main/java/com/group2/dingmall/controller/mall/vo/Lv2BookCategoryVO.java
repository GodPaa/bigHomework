package com.group2.dingmall.controller.mall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/6 21:41
 * @Description
 *     二级分类标签
 *     Level_2_BookCategory
 **/

@Data
public class Lv2BookCategoryVO {

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;


}
