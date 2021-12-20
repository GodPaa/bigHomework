package com.group2.dingmall.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.dao.admin.AdminCategoryMapper;
import com.group2.dingmall.po.BookCategory;
import com.group2.dingmall.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/14
 */
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{

    @Resource
    private AdminCategoryMapper adminCategoryMapper;
    //增加类别
    @Override
    public void addCategory(BookCategory category) {
        AssertUtil.isTrue(adminCategoryMapper.insert(category)<1,"添加目录错误");
    }

    //删除类别
    @Override
    public void deleteCategoryById(Long id) {
      AssertUtil.isTrue(adminCategoryMapper.deleteById(id)<1,"删除失败");
    }
    //获取类别列表
    @Override
    public List<BookCategory> getCategory() {
        List<BookCategory> categoryList = adminCategoryMapper.selectList(new QueryWrapper<BookCategory>());
        return categoryList;
    }
    //修改类别信息
    @Override
    public void updateCategory(BookCategory category) {
        long categoryId = category.getCategoryId();

        int upadte = adminCategoryMapper.update(category,new QueryWrapper<BookCategory>().eq("category_id",categoryId));

        AssertUtil.isTrue(upadte<1,"修改失败");
    }
}
