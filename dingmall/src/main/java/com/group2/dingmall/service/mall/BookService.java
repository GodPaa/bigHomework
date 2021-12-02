package com.group2.dingmall.service.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.dingmall.controller.mall.param.BookSearchParam;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.po.User;

public interface BookService {
    /**
     * 获取单个书本的信息
     * @param bookId
     * @return
     */
    BookInfoVO getBookInfo(Long bookId);

    /**
     * 分页（带检索条件）获取多个book的信息
     * @param
     * @return
     */
    IPage<BookInfoVO> searchBookPage(Page<BookInfoVO> page,BookSearchParam bookSearchParam);
}
