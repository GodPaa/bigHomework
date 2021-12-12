package com.group2.dingmall.service.mall;

import com.group2.dingmall.controller.mall.vo.Lv1BookCategoryVO;
import com.group2.dingmall.controller.mall.vo.Lv2BookCategoryVO;
import com.group2.dingmall.dao.BookCategoryMapper;
import com.group2.dingmall.utils.AssertUtil;
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
    public List<Lv1BookCategoryVO> getBookCategories() {

        List<Lv1BookCategoryVO> firstLevelBookCategoryList = bookCategoryMapper.getBookCategories();
        System.out.println(1);
        for(Lv1BookCategoryVO firstCategory : firstLevelBookCategoryList){
            /* 设置二级分类标签 */
            List<Lv2BookCategoryVO> secondLevelCategoryList = bookCategoryMapper.getSecondCategoryList(firstCategory.getCategoryId());
            firstCategory.setSecondLevelBookCategoryList(secondLevelCategoryList);
        }
        return firstLevelBookCategoryList;
    }

    /**
     * 用于获取首页的顶部分类标签
     * @return
     */
    @Override
    public List<Lv2BookCategoryVO> getFirstCategoryList() {
        List<Lv2BookCategoryVO> labelList = bookCategoryMapper.getFirstLevelCategoryList();
        AssertUtil.isTrue(labelList == null,"获取顶部导航信息出错");
        System.out.println(labelList.toString());
        return labelList;
    }
}
