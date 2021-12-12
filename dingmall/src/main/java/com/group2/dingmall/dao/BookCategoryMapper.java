package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.controller.mall.vo.Lv1BookCategoryVO;
import com.group2.dingmall.controller.mall.vo.Lv2BookCategoryVO;
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
     List<Lv2BookCategoryVO> getSecondCategoryList(long parentId);

     // 获取所有一级类别
     List<Lv1BookCategoryVO> getBookCategories();

     // 获得所有一级类别（只有类别name）
    List<Lv2BookCategoryVO> getFirstLevelCategoryList();

    // 根据类别ID查找 类别名
     String getNameById(long typeId);
}































