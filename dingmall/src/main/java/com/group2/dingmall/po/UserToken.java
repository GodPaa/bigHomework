package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserToken {
    // 主键
    @TableId(value = "user_id")
    private Long userId;

    private String token;

    private Date updateTime;

    private Date expireTime;
}