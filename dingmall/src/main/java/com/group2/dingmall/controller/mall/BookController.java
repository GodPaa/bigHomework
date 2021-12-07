package com.group2.dingmall.controller.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.dingmall.controller.mall.param.CertainTypeBookParam;
import com.group2.dingmall.controller.mall.vo.BookCategoryVO;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.controller.mall.param.BookSearchParam;
import com.group2.dingmall.service.mall.BookCategoryService;
import com.group2.dingmall.service.mall.BookServiceImpl;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/1 17:19
 * @Description
 **/

@RestController
@CrossOrigin
@Api(tags = "书籍管理相关接口")
@RequestMapping("book")
public class BookController {

    @Resource
    private BookServiceImpl bookService;
    @Resource
    private BookCategoryService bookCategoryService;

    /**
     *
     * 图书详情接口（获取某个商品的详细信息）
     *      根据id查找book，返回书本详情
     *      和新蜂商城不一样的： 不用验证用户 token
     *                       等用户加入book到购物车或者购买的时候再验证token  
     * @param bookId
     * @return
     */
    @GetMapping("/detail/{bookId}")
    @ApiOperation(value = "查询书本详情接口", notes = "返回书本详情,传过来的参为bookId")
    public Result getBookDetail(@PathVariable("bookId") Long bookId){
        Result result = new Result();
        BookInfoVO bookInfoVO = bookService.getBookInfo(bookId);
        result.setData(bookInfoVO);
        return result;
    }

    /**
     * 图书查询页面接口
     *       根据关键字检索书本，返回多条查询结果
     * @param bookSearchParam
     * @return
     */
    @GetMapping("/search")
    @ApiOperation(value = "关键字搜索书本接口", notes = "返回搜索结果：根据关键字、书标签、作者进行检索，也可以按time和price排序,默认按评分排序")
    public Result search(BookSearchParam bookSearchParam){
        Result result = new Result();
        Page<BookInfoVO> page = new Page<>(bookSearchParam.getPageNumber() , bookSearchParam.getPageSize());
        IPage<BookInfoVO> bookIPage =  bookService.searchBookPage(page,bookSearchParam);
        result.setData(bookIPage);
        return result;
    }

    /**
     *  图书分类查询结果接口
     *          用户点击分类标签，后台返回 该类别的 list<书>
     * @param certainTypeBookParam
     * @return
     */
    @GetMapping("/certainType")
    @ApiOperation(value = "图书分类查询结果接口",notes = "用户点击类别标签，返回某一类的list<书本>")
    public Result getBookPageByType(@Valid CertainTypeBookParam certainTypeBookParam){
        Result result = new Result();
        Page<BookInfoVO> page = new Page<>(certainTypeBookParam.getPageNumber(),certainTypeBookParam.getPageSize());
        IPage<BookInfoVO> bookPage =  bookService.getBookPageByType(page,certainTypeBookParam.getBookType());
        result.setData(bookPage);
        return result;
    }


    /**
     * 分类标签页面接口：
     *      返回一级标签和二级标签
     * @return
     */
    @GetMapping("/categories")
    @ApiOperation(value = "分类标签页面接口",notes = "返回一级标签和二级标签")
    public Result getBookCategories(){
        Result result = new Result();
        List<BookCategoryVO> bookCategoryVOList = bookCategoryService.getBookCategories();
        result.setData(bookCategoryVOList);
        return result;
    }






}
