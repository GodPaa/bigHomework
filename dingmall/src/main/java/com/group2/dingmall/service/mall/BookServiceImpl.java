package com.group2.dingmall.service.mall;

import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.dao.BookMapper;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
import com.group2.dingmall.utils.IPUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author lv
 * @Date 2021/12/2 11:06
 * @Description
 **/
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    /**
     * 获取书本详细信息
     * @param bookId
     * @return
     */
    @Override
    public BookInfoVO getBookInfo(Long bookId) {
        /* 参数校验 */
        AssertUtil.isTrue(0 > bookId,"参数异常");
        Book book = bookMapper.selectById(bookId);
        AssertUtil.isTrue(book == null,"查无此书");

        /* 封装返回的data */
        BookInfoVO bookInfoVO = new BookInfoVO();
        BeanUtil.copyProperties(book, bookInfoVO);
        // 拼接图片访问地址，我放在 Apache 服务器上
        String ImgUrl = String.format("http://%s/pic/%s/%s.jpg", IPUtil.getCurrentIP(),book.getBookType(), book.getBookName());
        bookInfoVO.setBookImgUrl(ImgUrl);
        return bookInfoVO;
    }
}
