package com.group2.dingmall.controller.mall.param;

/**
 * @Author lv
 * @Date 2021/12/2 12:52
 * @Description
 **/

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import lombok.Data;
import lombok.Value;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * 搜索book的参数
 */
@Data
public class BookSearchParam {
    @ApiModelProperty("书本标签")
    private String label;

    @ApiModelProperty("书作者")
    private String author;

    @ApiModelProperty("关键字")
    private String keyword;

    @ApiModelProperty("页码(默认是1，即显示第一页)")
    private Integer pageNumber = 1;

    @ApiModelProperty("每页显示多少条信息（默认是10，即每一页显示10条数据）")
    private Integer pageSize = 10;

    @ApiModelProperty("排序规则，只能是可以是time或price")
    private String orderBy;
}
