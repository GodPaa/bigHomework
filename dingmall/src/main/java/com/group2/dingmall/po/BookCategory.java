package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BookCategory对象", description="")
public class BookCategory implements Serializable {

  private static final long serialVersionUID=1L;

  @ApiModelProperty(value = "分类id")
  @TableId(value = "category_id", type = IdType.AUTO)
  private Long categoryId;

  @ApiModelProperty(value = "分类级别(1-一级分类 2-二级分类)")
  private Integer categoryLevel;

  @ApiModelProperty(value = "父分类id")
  private Long parentId;

  @ApiModelProperty(value = "分类名称")
  private String categoryName;

  @ApiModelProperty(value = "删除标识字段(0-未删除 1-已删除)")
  private Integer isDeleted;

  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @ApiModelProperty(value = "创建者id")
  private Integer createUser;

  @ApiModelProperty(value = "修改时间")
  private Date updateTime;

  @ApiModelProperty(value = "修改者id")
  private Integer updateUser;

}
