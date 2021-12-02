package com.group2.dingmall.controller.user;

import com.group2.dingmall.config.annotation.TokenToUser;
import com.group2.dingmall.controller.user.param.UserRegisterParam;
import com.group2.dingmall.controller.user.param.UserUpdateParam;
import com.group2.dingmall.controller.user.vo.UserInfoVO;
import com.group2.dingmall.controller.user.vo.UserLoginVO;
import com.group2.dingmall.controller.user.param.UserLoginParam;
import com.group2.dingmall.po.User;
import com.group2.dingmall.utils.Result;
import com.group2.dingmall.service.user.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("user")
@Api(tags = "用户管理相关接口")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;

    // 登录请求
    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
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
    public Result register(@ApiParam("用户注册信息") @Valid UserRegisterParam userRegisterParam) {

        Result result = new Result<>();

        userServiceImpl.register(userRegisterParam.getLoginName(),userRegisterParam.getPassword());

        result.setMsg("注册成功");

        return result;
    }

    // 修改用户信息
    @PutMapping("/info")
    @ApiOperation(value = "修改用户信息（不能改loginName和id）", notes = "")
    public Result updateInfo(@ApiParam("用户更新信息") @Valid  UserUpdateParam userUpdateParam, @TokenToUser User user) {

        Result result = new Result();

        userServiceImpl.update(userUpdateParam,user.getUserId());

        return result;
    }

    // 获取用户信息
    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public Result getUserDetail(@TokenToUser User user) {
        //已登录则直接返回
        Result result = new Result();
        UserInfoVO userInfoVO = userServiceImpl.getUserInfo(user);
        result.setData(userInfoVO);
        return result;
    }

    // 用户退出
    @PostMapping("/logout")
    @ApiOperation(value = "登出接口", notes = "清除token")
    public Result<String> logout(@TokenToUser User user) {
        Result result = new Result();
        userServiceImpl.logout(user.getUserId());
        return result;
    }









}
