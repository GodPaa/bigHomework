package com.group2.dingmall.controller.admin;

import com.group2.dingmall.po.User;
import com.group2.dingmall.service.admin.AdminUserService;
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
 * Date:2021/12/13
 */
@RestController
@CrossOrigin
@Api(tags = "管理用户接口")
public class AdminUserController {
    @Resource
    AdminUserService adminUserService;
    // 增加用户

    @PostMapping("/adminUser")
    @ApiOperation(value = "insert",notes="增加用户")
    public Result insertUser(User user){

        adminUserService.addUser(user);

        return new Result();
    }

    // 删除用户

    @DeleteMapping("/adminUser")
    @ApiOperation(value = "delete",notes = "删除用户（根据ID）")
    public Result deleteUser(Long id){
        adminUserService.deleteUserById(id);

        return new Result();
    }
    // 查询用户
//    根据ID查
    @GetMapping("/adminUser/{userId}")
    @ApiOperation(value = "selectById", notes = "根据ID查询用户")
    public Result getUserById(@PathVariable("userId") Long userId){
        Result result = new Result();
        User user= adminUserService.getUserById(userId);

        result.setData(user);
        return result;
    }

    //直接查询用户列表 或 根据id查询用户相关信息
    @GetMapping("/adminUser")
    @ApiOperation(value = "select",notes = "查询所有用户信息")
    public Result selectUserById(){
        Result result = new Result();
        List<User> list=adminUserService.getUser();

        result.setData(list);//将结果设成result数据返回输出
        return result;

    }

    // 修改用户信息
    @PutMapping("/adminUser")
    @ApiOperation(value = "update",notes = "更新用户信息(根据ID)")
    public Result updateUser(User user){

        adminUserService.updateUser(user);

        return new Result();
    }
}
