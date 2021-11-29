package com.group2.dingmall.controller.mall;

import com.group2.dingmall.controller.mall.param.UserRegisterParam;
import com.group2.dingmall.controller.mall.vo.UserLoginVO;
import com.group2.dingmall.controller.mall.param.UserLoginParam;
import com.group2.dingmall.utils.Result;
import com.group2.dingmall.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    @ApiOperation(value = "登录接口", notes = "返回加密过的Id和用户名")
    public Result userLogin(@Valid UserLoginParam user){

        // 实例化统一响应结果
        Result result = new Result<>();

        // 调用service层的登录方法
        UserLoginVO userLoginVO = userServiceImpl.login(user.getLoginName(),user.getPassword());

        // 设置响应结果的result的值 （将数据返回用户对象）
        result.setData(userLoginVO);

        return result;
    }


    // 注册请求
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "")
    public Result register(@Valid UserRegisterParam userRegisterParam) {

        Result result = new Result<>();

        userServiceImpl.register(userRegisterParam.getLoginName(),userRegisterParam.getPassword());

        result.setMsg("注册成功");

        return result;
    }






}
