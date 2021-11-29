package com.group2.xxx.controller.mall;

import com.group2.xxx.controller.mall.model.UserLoginModel;
import com.group2.xxx.controller.mall.param.UserLoginParam;
import com.group2.xxx.utils.Result;
import com.group2.xxx.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author lv
 * @Date 2021/11/15 19:41
 * @Description
 **/
@RestController
@CrossOrigin
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;

    // 登录请求
    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "返回UserLoginModel")
    public Result userLogin(UserLoginParam user){

        System.out.println(user.toString());

        // 实例化统一响应结果
        Result result = new Result<>();

        // 调用service层的登录方法
        UserLoginModel userLoginModel = userServiceImpl.login(user.getLoginName(),user.getPasswordMd5());

        // 设置响应结果的result的值 （将数据返回用户对象）
        result.setData(userLoginModel);

        return result;
    }





}
