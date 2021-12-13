package com.group2.dingmall.controller.mall;

import com.group2.dingmall.po.Book;
import com.group2.dingmall.po.Comment;
import com.group2.dingmall.service.mall.BookService;
import com.group2.dingmall.service.mall.CommentService;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */

@RestController
@CrossOrigin
@Api(tags = "书籍评论相关接口")
public class CommentController {

    @Resource
    private CommentService commentService;




    @GetMapping("/comment/{bookId}")
    @ApiOperation(value = "书本评论接口", notes = "返回书本评论,传过来的参为bookId")
    public Result getBookDetail(@PathVariable("bookId") Long bookId){
        Result result = new Result();
        List<Comment> commentList = commentService.getCommentByBookId(bookId);
        result.setData(commentList);
        return result;
    }

}

