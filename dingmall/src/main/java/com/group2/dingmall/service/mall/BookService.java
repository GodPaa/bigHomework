package com.group2.dingmall.service.mall;

import com.group2.dingmall.controller.mall.vo.BookInfoVO;

public interface BookService {
    BookInfoVO getBookInfo(Long bookId);
}
