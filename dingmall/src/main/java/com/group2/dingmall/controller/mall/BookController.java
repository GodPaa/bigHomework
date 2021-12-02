package com.group2.dingmall.controller.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.controller.mall.param.BookSearchParam;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.service.mall.BookService;
import com.group2.dingmall.service.mall.BookServiceImpl;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "书本详情接口", notes = "传过来的参为bookId")
    public Result getBookDetail(@ApiParam(value = "book_id") @PathVariable("bookId") Long bookId){
        Result result = new Result();
        BookInfoVO bookInfoVO = bookService.getBookInfo(bookId);
        result.setData(bookInfoVO);
        return result;
    }


    /**
     * 图书查询页面接口
     *       根据关键字检索书本，返回匹配的多条数据
     * @param bookSearchParam
     * @return
     */
    @GetMapping("/search")
    @ApiOperation(value = "书本搜索接口", notes = "根据关键字、书类别、作者进行检索，也可以按time和price排序")
    public Result search(BookSearchParam bookSearchParam){
        Result result = new Result();

        Page<BookInfoVO> page = new Page<>(bookSearchParam.getPageNumber() , bookSearchParam.getPageSize());

        IPage<BookInfoVO> userIPage =  bookService.searchBookPage(page,bookSearchParam);

        result.setData(userIPage);

        return result;
    }
}
