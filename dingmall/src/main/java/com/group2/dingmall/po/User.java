package com.group2.dingmall.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@ApiModel(description = "用户字段")
public class User {
  // 主键，自增 ，不加注解找不到主键
  @TableId(value = "user_id",type = IdType.AUTO)
  private Long userId;
  private String nickName;
  private String loginName;
  private String passwordMd5;
  private String introduceSign;
  private long isDeleted;
  private long lockedFlag;
  private java.sql.Timestamp createTime;
}
