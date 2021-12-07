package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BookCategory {
  // 主键
  @TableId(value = "id")
  private long categoryId;
  private long categoryLevel;
  private long parentId;
  private String categoryName;
  private long isDeleted;
  private java.sql.Timestamp createTime;
  private long createUser;
  private java.sql.Timestamp updateTime;
  private long updateUser;

}
