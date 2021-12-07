package com.group2.dingmall.service.mall;

import com.group2.dingmall.controller.mall.vo.BookCategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCategoryService {
    /**
     * 获取 list<书本类别> , 用于书本分类页面的 类别导航栏
     * @return
     */
    List<BookCategoryVO> getBookCategories();
}
