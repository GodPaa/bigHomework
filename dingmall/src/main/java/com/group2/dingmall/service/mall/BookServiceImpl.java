package com.group2.dingmall.service.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.dingmall.controller.mall.param.BookSearchParam;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.dao.BookMapper;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
import com.group2.dingmall.utils.IPUtil;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 分页（带检索条件）获取多个 book 的信息
     * @param bookSearchParam
     * @return
     */
    @Override
    public IPage<BookInfoVO> searchBookPage(Page<BookInfoVO> page,BookSearchParam bookSearchParam) {

        /* 搜索参数都为空，直接返回异常 */
        String keyword = bookSearchParam.getKeyword();
        String bookType = bookSearchParam.getBookType();
        String bookAuthor = bookSearchParam.getBookAuthor();
        AssertUtil.isTrue(StringUtils.isBlank(keyword) &&
                               StringUtils.isBlank(bookType) &&
                               StringUtils.isBlank(bookAuthor)
                            ,"搜索参数不能为空");

        /* 滤去空格 */
        if (keyword != null) {
            bookSearchParam.setKeyword(keyword.trim());
        }
        if (bookType != null) {
            bookSearchParam.setBookType(bookType.trim());
        }
        if (bookAuthor != null) {
            bookSearchParam.setBookAuthor(bookAuthor.trim());
        }

        /* 调用 dao 层的方法 */
        IPage<BookInfoVO> pages = bookMapper.searchBookPage(page,bookSearchParam);

        /* 加上图片url */
        for (BookInfoVO bookInfoVO : pages.getRecords()){
            String ImgUrl = String.format("http://%s/pic/%s/%s.jpg", IPUtil.getCurrentIP(),bookInfoVO.getBookType(), bookInfoVO.getBookName());
            bookInfoVO.setBookImgUrl(ImgUrl);
        }

        return pages;
    }
}
