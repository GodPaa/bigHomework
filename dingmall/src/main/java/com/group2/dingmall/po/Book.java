package com.group2.dingmall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {

  // 主键，自增 ，不加注解找不到主键
  @TableId(value = "book_id",type = IdType.AUTO)
  private Long bookId;
  private String bookName;
  private String bookType;
  private String bookAuthor;
  private long bookStock;
  private long bookPrice;
  private String bookIntro;
  private long bookStatus;
  private long createUser;
  private java.sql.Timestamp createTime;
  private long updateUser;
  private java.sql.Timestamp updateTime;


}
