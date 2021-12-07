package com.group2.dingmall.service.mall;

import com.group2.dingmall.controller.mall.vo.BookCategoryVO;
import com.group2.dingmall.dao.BookCategoryMapper;
import com.group2.dingmall.utils.IPUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/6 22:17
 * @Description
 **/
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Resource
    BookCategoryMapper bookCategoryMapper;

    /**
     * 用于获取 书本分类页面导航栏的  list<book_type>
     * @return
     */
    @Override
    public List<BookCategoryVO> getBookCategories() {

        List<BookCategoryVO> firstLevelBookCategoryList = bookCategoryMapper.getBookCategories();
        System.out.println(1);
        for(BookCategoryVO firstCategory : firstLevelBookCategoryList){
            /* 设置二级分类标签 */
            List<String> secondLevelCategoryList = bookCategoryMapper.getSecondCategoryList(firstCategory.getCategoryId());
            firstCategory.setSecondLevelBookCategoryList(secondLevelCategoryList);
        }
        return firstLevelBookCategoryList;
    }
}
