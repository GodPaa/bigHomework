package com.group2.dingmall.controller.user;


import com.group2.dingmall.controller.user.param.UserAddressParam;
import com.group2.dingmall.controller.user.vo.UserAddressVO;
import com.group2.dingmall.service.user.UserAddressService;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.NumberUtil;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
        // 获取user
        String loginName = principal.getName();
        // 操作
        UserAddressVO userAddressVO = userAddressService.getUserAddress(loginName);
        Result result = new Result();
        result.setData(userAddressVO);
        return result;
    }


    @PostMapping("/address")
    @ApiOperation(value = "添加地址", notes = "参数为用户地址添加表单")
    public Result addUserAddress(@RequestBody @Valid UserAddressParam userAddressParam, Principal principal){
        // 参数验证
        checkAddressParam(userAddressParam);
        // 获取user
        String loginName = principal.getName();
        // 操作
        userAddressService.addUserAddress(userAddressParam,loginName);
        return new Result();
    }

    @PutMapping("/address")
    @ApiOperation(value = "修改地址", notes = "")
    public Result updateUserAddress(@RequestBody @Valid UserAddressParam userAddressParam,Principal principal){
        // 参数验证
        checkAddressParam(userAddressParam);
        // 获取user
        String loginName = principal.getName();
        // 操作
        userAddressService.updateUserAddress(userAddressParam,loginName);
        return new Result();
    }


    @DeleteMapping("/address")
    @ApiOperation(value = "删除收货地址", notes = "删除当前用户的收获地址信息")
    public Result deleteUserAddress(Principal principal){
        // 获取user
        String loginName = principal.getName();
        // 删除操作
        userAddressService.deleteUserAddress(loginName);
        return new Result();
    }


    /**
     * 地址参数验证
     * @param userAddressParam
     */
    private void checkAddressParam(UserAddressParam userAddressParam) {
        String phone = userAddressParam.getUserPhone();
        AssertUtil.isTrue(!NumberUtil.isPhone(phone),"收货人手机格式不正确");
    }
}

