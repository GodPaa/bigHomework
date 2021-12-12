package com.group2.dingmall.service.mall;

import com.group2.dingmall.controller.mall.vo.Lv1BookCategoryVO;
import com.group2.dingmall.controller.mall.vo.Lv2BookCategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCategoryService {
    /**
     * 获取 list<书本类别> , 用于书本分类页面的 类别导航栏
     * @return
     */
    List<Lv1BookCategoryVO> getBookCategories();

    /**
     *  获取一级分类标签
     *      用于首页顶部导航栏
      * @return
     */
    List<Lv2BookCategoryVO> getFirstCategoryList();
}
