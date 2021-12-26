package com.group2.dingmall.service.admin;

import com.group2.dingmall.controller.admin.param.BookParam;
import com.group2.dingmall.po.Book;

import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/14
 */
public interface AdminBookService {
    //添加目录
    void addBook(BookParam book);
    //删除目录（根据ID）
    void deleteBookById(Integer id);
    //获取目录列表
    List<Book> getBook();
    //修改目录信息（根据ID）
    void updateBook(Book book);
    //根据ID查询书本信息
    Book getBookById(Integer bookId);
}
