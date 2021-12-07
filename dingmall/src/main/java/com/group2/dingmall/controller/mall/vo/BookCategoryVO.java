package com.group2.dingmall.controller.mall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/6 21:41
 * @Description
 *     分类标签页面需要的数据
 **/

@Data
public class BookCategoryVO {

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("二级分类标签列表")
    private List<String> secondLevelBookCategoryList;
}
