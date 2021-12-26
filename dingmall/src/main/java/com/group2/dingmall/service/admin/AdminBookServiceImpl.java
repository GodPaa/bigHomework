package com.group2.dingmall.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.controller.admin.param.BookParam;
import com.group2.dingmall.dao.admin.AdminBookMapper;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
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
public class AdminBookServiceImpl implements AdminBookService{
    @Resource
    private AdminBookMapper adminBookMapper;
    @Override
    public void addBook(BookParam book) {
        Book book1 = new Book();
        BeanUtil.copyProperties(book,book1);
        AssertUtil.isTrue(adminBookMapper.insert(book1)<1,"添加书本错误");
    }

    @Override
    public void deleteBookById(Integer id) {
        AssertUtil.isTrue(adminBookMapper.deleteById(id)<1,"删除失败");
    }

    @Override
    public List<Book> getBook() {
        List<Book> bookList = adminBookMapper.selectList(new QueryWrapper<Book>());
        return bookList;
    }

    @Override
    public void updateBook(Book book) {
        long bookId = book.getId();

        Book book1= new Book();
        BeanUtil.copyProperties(book,book1);
        int upadte = adminBookMapper.update(book1,new QueryWrapper<Book>().eq("id",bookId));

        AssertUtil.isTrue(upadte<1,"修改失败");
    }

    @Override
    public Book getBookById(Integer bookId) {
        Book book = adminBookMapper.selectById(bookId);
        return book;
    }
}
