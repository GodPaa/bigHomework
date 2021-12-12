package com.group2.dingmall.controller.user;


import com.group2.dingmall.controller.user.param.UserAddressParam;
import com.group2.dingmall.controller.user.vo.UserAddressVO;
import com.group2.dingmall.service.user.UserAddressService;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author lv
 * @since 2021-12-12
 */
@RestController
@CrossOrigin
@Api(tags = "用户地址相关接口")
@RequestMapping("/user-address")
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;


    @GetMapping("/address")
    @ApiOperation(value = "我的收货地址", notes = "无参数，需要header有token")
    public Result getUserAddress(Principal principal){
        Result result = new Result();
        // 获取user
        String loginName = principal.getName();
        // 操作
        UserAddressVO userAddressVO = userAddressService.getUserAddress(loginName);
        result.setData(userAddressVO);
        return result;
    }


    @PostMapping("/address")
    @ApiOperation(value = "添加地址", notes = "参数为用户地址添加表单")
    public Result addUserAddress(UserAddressParam userAddressParam,Principal principal){
        // 获取user
        String loginName = principal.getName();
        // 操作
        userAddressService.addUserAddress(userAddressParam,loginName);
        return new Result();
    }
//
//    @PutMapping("/address")
//    @ApiOperation(value = "修改地址", notes = "")
//
//    @GetMapping("/address/{addressId}")
//    @ApiOperation(value = "获取收货地址详情", notes = "传参为地址id")
//
//    @DeleteMapping("/address/{addressId}")
//    @ApiOperation(value = "删除收货地址", notes = "传参为地址id")
}

