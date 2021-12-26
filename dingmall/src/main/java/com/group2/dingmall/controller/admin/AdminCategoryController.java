package com.group2.dingmall.controller.admin;

import com.group2.dingmall.controller.admin.param.CategoryParam;
import com.group2.dingmall.po.BookCategory;
import com.group2.dingmall.service.admin.AdminCategoryService;
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
@Api(tags = "管理员书本类别接口")
public class AdminCategoryController {
   @Resource
   AdminCategoryService adminCategoryService;

   //增加类别
   @PostMapping("/adminCategory")
   @ApiOperation(value = "insert",notes="增加类别")
   public Result addCategory(CategoryParam bookCategory){

       adminCategoryService.addCategory(bookCategory);

       return new Result();

   }

    // 删除类别

    @DeleteMapping("/adminCategory")
    @ApiOperation(value = "delete",notes = "删除类别")
    public Result deleteCategory(Long id){

        adminCategoryService.deleteCategoryById(id);

        return new Result();
    }



    // 查询类别
    //直接查询类别列表
    @GetMapping("/adminCategory")
    @ApiOperation(value = "select",notes = "查询类别列表")
    public Result selectCategoryById(){
        Result result = new Result();
        List<BookCategory> list=adminCategoryService.getCategory();

        result.setData(list);//将结果设成result数据返回输出

        return result;
    }

    // 修改类别信息
    @PutMapping("/adminCategory")
    @ApiOperation(value = "update",notes = "更新类别信息(根据ID)")
    public Result updateCategory(BookCategory bookCategory){

        adminCategoryService.updateCategory(bookCategory);

        return new Result();
    }

}
