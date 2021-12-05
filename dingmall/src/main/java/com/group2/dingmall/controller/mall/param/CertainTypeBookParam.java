package com.group2.dingmall.controller.mall.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author lv
 * @Date 2021/12/5 17:40
 * @Description
 *    分类页面，选择某一类书籍，前台要给后台的参数
 **/
@Data
public class CertainTypeBookParam {
    @ApiModelProperty("书本类别")
    @NotEmpty(message = "书本类别不能为空")
    private String bookType;

    @ApiModelProperty("页码(默认是1，即显示第一页)")
    private Integer pageNumber = 1;

    @ApiModelProperty("每页显示多少条信息（默认是10，即每一页显示10条数据）")
    private Integer pageSize = 10;
}
