package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "评论的id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "书的id")
    private Integer bookId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "评分")
    private String score;

    @ApiModelProperty(value = "评论时间")
    private String time;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "赞同数")
    private Integer approval;


}
