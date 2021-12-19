package com.group2.dingmall.controller.user;

import com.group2.dingmall.controller.user.vo.OrderInfoVO;
import com.group2.dingmall.service.user.OrderService;
import com.group2.dingmall.service.user.UserAddressService;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @Author lv
 * @Date 2021/12/19 13:48
 * @Description
 **/
@RestController
@CrossOrigin

@Api(tags = "确认订单界面接口")
public class OrderController {

    @Resource
    private UserAddressService addressService;
    @Resource
    private OrderService orderService;


    @GetMapping("/order/{orderId}")
    @ApiOperation(value = "获取订单详情", notes = "根据订单id查询订单详情，用于确认订单界面以及代付款界面（订单未支付）")
    public Result getOrderInfo(@PathVariable long orderId, Principal principal){
        String loginName = principal.getName();

        OrderInfoVO orderInfoVO = orderService.getOrderInfo(loginName,orderId);

        return new Result(orderInfoVO);
    }

    @GetMapping("/payment/{orderId}")
    @ApiOperation(value = "跳转支付宝接口", notes = "返回一段js代码，放在div里面就能跳转")
    public String payment(@PathVariable long orderId, Principal principal){
        String loginName = principal.getName();

        String js = orderService.payment(loginName,orderId);

        return js;
    }
}
