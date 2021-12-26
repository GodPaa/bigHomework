package com.group2.dingmall.service.admin;

import com.group2.dingmall.controller.admin.param.CategoryParam;
import com.group2.dingmall.po.BookCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/14
 */
@Service
public interface AdminCategoryService {
    //添加类别
    void addCategory(CategoryParam category);
    //删除类别（根据ID）
    void deleteCategoryById(Long id);
    //获取类别列表
    List<BookCategory> getCategory();
    //修改类别信息（根据ID）
    void updateCategory(BookCategory category);
}
