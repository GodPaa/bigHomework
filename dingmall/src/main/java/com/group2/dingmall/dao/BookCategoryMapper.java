package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.controller.mall.vo.BookCategoryVO;
import com.group2.dingmall.po.BookCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/6 22:09
 * @Description
 **/
@Mapper
public interface BookCategoryMapper extends BaseMapper<BookCategory> {
     // 获取所有二级类别
     List<String> getSecondCategoryList(long parentId);

     // 获取所有一级类别
     List<BookCategoryVO> getBookCategories();
}































