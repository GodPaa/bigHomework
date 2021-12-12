package com.group2.dingmall.controller.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.dingmall.controller.mall.vo.CarouselInfoVO;
import com.group2.dingmall.controller.mall.vo.Lv2BookCategoryVO;
import com.group2.dingmall.dao.BookCategoryMapper;
import com.group2.dingmall.dao.BookMapper;
import com.group2.dingmall.po.BookCategory;
import com.group2.dingmall.service.mall.BookCategoryService;
import com.group2.dingmall.service.mall.BookService;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/5 16:53
 * @Description
 *
 *   获取首页内容
 **/


@RestController
@CrossOrigin
@RequestMapping("index")
@Api(tags = "首页相关接口")
public class IndexController {
    @Resource
    BookService bookService;
    @Resource
    BookCategoryService bookCategoryService;


    // 1. 顶部分类导航栏接口
    // 6个一个级分类标签
    @GetMapping("nav")
    @ApiOperation(value = "顶部导航栏接口",notes = "获取书本的一级分类标签")
    public Result getTopNav(){
        Result result = new Result();
        List<Lv2BookCategoryVO> labelList =  bookCategoryService.getFirstCategoryList();
        result.setData(labelList);
        return result;
    }




    // 2. 轮播图接口
    // 总共50本书，分5页，每页10张
    // 参数 ： 页数（第几页）
    @GetMapping("/carouse")
    @ApiOperation(value = "首页轮播图接口",notes = "固定只提供50本书的信息，每页10本，分5页，参数为页数")
    public Result getCarouselInfo(long pageNum){

        // 不知道为什么 validate校验参数不行，这里手动校验
        // todo 非空 非数字
        AssertUtil.isTrue((pageNum < 1 || pageNum > 5),"输入的页面参数出错");

        Result result = new Result();
        Page<CarouselInfoVO> page = new Page<>(pageNum,10);
        IPage<CarouselInfoVO> bookPage =  bookService.getIndexBook(page);
        result.setData(bookPage);
        return result;
    }

}
