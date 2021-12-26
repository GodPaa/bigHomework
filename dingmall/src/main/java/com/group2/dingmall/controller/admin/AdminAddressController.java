package com.group2.dingmall.controller.admin;

import com.group2.dingmall.controller.admin.param.AddressParam;
import com.group2.dingmall.controller.admin.param.BookParam;
import com.group2.dingmall.controller.user.param.UserAddressParam;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.po.UserAddress;
import com.group2.dingmall.service.admin.AdminAddressService;
import com.group2.dingmall.service.admin.AdminBookService;
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
 * Date:2021/12/26
 */
@RestController
@CrossOrigin
@Api(tags="管理员用户地址接口")
public class AdminAddressController {
    @Resource
    AdminAddressService adminAddressService;

    //为用户增加地址
    @PostMapping("/adminAddress")
    @ApiOperation(value = "insert", notes = "为用户增加地址")
    public Result addAddress(AddressParam address) {

        adminAddressService.addAddress(address);

        return new Result();

    }

    // 删除用户地址

    @DeleteMapping("/adminAddress")
    @ApiOperation(value = "delete", notes = "删除地址(根据ID）")
    public Result deleteAddress(Integer id) {

        adminAddressService.deleteAddressById(id);

        return new Result();
    }

    // 查询地址
//    根据ID查
    @GetMapping("/adminAddress/{user_Id}")
    @ApiOperation(value = "selectById", notes = "根据ID查询用户地址信息")
    public Result getBookDetail(@PathVariable("user_Id") Integer userId) {
        Result result = new Result();
        UserAddress userAddress = adminAddressService.getAddressById(userId);

        result.setData(userAddress);
        return result;
    }

    // 查询用户地址列表
    //直接查询列表 或 根据种类名称查询相关信息
    @GetMapping("/adminAddress")
    @ApiOperation(value = "select", notes = "查询所有用户地址")
    public Result selectBookById() {
        Result result = new Result();

        List<UserAddress> list = adminAddressService.getAddress();

        result.setData(list);//将结果设成result数据返回输出

        return result;
    }

    // 修改地址信息
    @PutMapping("/adminAddress")
    @ApiOperation(value = "update", notes = "更新地址信息（输入用户ID，其他信息任意修改）")
    public Result updateBook(AddressParam addressParam) {

       adminAddressService.updateAddress(addressParam);

        return new Result();

    }
}
