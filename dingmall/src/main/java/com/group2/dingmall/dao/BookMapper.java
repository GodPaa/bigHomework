package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.dingmall.controller.mall.param.BookSearchParam;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.po.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lv
 * @Date 2021/12/2 10:49
 * @Description
 **/
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    // 获取所有User，并分页
    IPage<BookInfoVO> searchBookPage(Page<BookInfoVO> page,BookSearchParam bookSearchParam);
}
