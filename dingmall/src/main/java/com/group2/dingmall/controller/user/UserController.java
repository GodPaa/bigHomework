package com.group2.dingmall.controller.user;


import com.group2.dingmall.controller.user.param.UserRegisterParam;
import com.group2.dingmall.controller.user.param.UserUpdateParam;
import com.group2.dingmall.controller.user.vo.UserInfoVO;
import com.group2.dingmall.controller.user.vo.UserLoginVO;
import com.group2.dingmall.controller.user.param.UserLoginParam;
import com.group2.dingmall.po.User;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.Result;
import com.group2.dingmall.service.user.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

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
    public Result userLogin(@Valid @RequestBody UserLoginParam user, HttpServletRequest request){
        // 实例化统一响应结果
        Result result = new Result<>();
        // 调用service层的登录方法
        UserLoginVO userLoginVO = userServiceImpl.login(user.getLoginName(),user.getPassword(),request);
        // 设置响应结果的result的值 （将数据返回用户对象）
        result.setData(userLoginVO);

        return result;
    }

    // 获取当前用户信息
    @GetMapping("/info")
    @ApiOperation(value = "获取当前登录用户的信息")
    public Result getUserDetail(Principal principal) {
        // 未登录则返回
        AssertUtil.isTrue(principal == null,"token为null");
        String username = principal.getName();
        Result result = new Result();
        User user = userServiceImpl.getUserInfo(username);
        result.setData(user);
        return result;
    }


    // 注册请求
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "")
    public Result register(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        Result result = new Result<>();
        userServiceImpl.register(userRegisterParam.getLoginName(),userRegisterParam.getPassword());
        result.setMsg("注册成功");
        return result;
    }

    // 修改用户信息
    @PutMapping("/info")
    @ApiOperation(value = "修改用户信息（name参数是没用的）", notes = "需要请求头token和修改表单的信息")
    public Result updateInfo(@Valid  @RequestBody UserUpdateParam userUpdateParam, Principal principal) {

        String loginName = principal.getName();
        Result result = new Result();
        userServiceImpl.update(userUpdateParam,loginName);
        return result;
    }



    // 用户退出
    @PostMapping("/logout")
    @ApiOperation(value = "登出接口", notes = "清除token")
    public Result<String> logout(long id) {
        Result result = new Result();
        return result;
    }









}
