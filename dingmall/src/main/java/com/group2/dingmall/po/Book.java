package com.group2.dingmall.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {

  // 主键，自增 ，不加注解找不到主键
  @TableId(value = "id",type = IdType.AUTO)
  private long id;
  private String bookName;
  private String author;
  private String publishingHouse;
  private String translator;
  private String yearOfPublication;
  private long pages;
  private String isbn;
  private String originalPrice;
  private String currentPrice;
  private double score;
  private long numberOfPeople;
  private String briefIntroduction;
  private String authorIntroduction;
  private String catalog;
  private String label;
  private String imgUrl;
  private long bookStatus;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


}
