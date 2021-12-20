package com.group2.dingmall.controller.admin;

import com.group2.dingmall.po.Book;
import com.group2.dingmall.service.admin.AdminBookService;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/14
 */
@RestController
@CrossOrigin
@Api(tags="管理员书本接口")
public class AdminBookController {
    @Resource
    AdminBookService adminBookService;
    //增加书本
    @PostMapping("/adminBook")
    @ApiOperation(value = "insert",notes="增加书本")
    public Result addBook(Book book){

        adminBookService.addBook(book);

        return new Result();

    }

    // 删除书本

    @DeleteMapping("/adminBook")
    @ApiOperation(value = "delete",notes = "删除书本(根据ID）")
    public Result deleteBook(Integer id){

        adminBookService.deleteBookById(id);

        return new Result();
    }

    // 查询书本
//    根据ID查
    @GetMapping("/adminBook/{bookId}")
    @ApiOperation(value = "selectById", notes = "根据ID查询书本")
    public Result getBookDetail(@PathVariable("bookId") Integer bookId){
        Result result = new Result();
        Book book = adminBookService.getBookById(bookId);

        result.setData(book);
        return result;
    }
    // 查询书本
    //直接查询列表 或 根据种类名称查询相关信息
    @GetMapping("/adminBook")
    @ApiOperation(value = "select",notes = "查询所有书本")
    public Result selectBookById(){
        Result result = new Result();

        List<Book> list=adminBookService.getBook();

        result.setData(list);//将结果设成result数据返回输出

        return result;
    }

    // 修改书本信息
    @PutMapping("/adminBook")
    @ApiOperation(value = "update",notes = "更新书本信息（输入书本ID，其他信息任意修改）")
    public Result updateBook(Book book){

        adminBookService.updateBook(book);

        return new Result();

    }

}
