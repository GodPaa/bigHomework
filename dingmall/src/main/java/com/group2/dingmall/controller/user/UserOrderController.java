package com.group2.dingmall.controller.user;


import com.group2.dingmall.service.user.UserOrderService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */
@RestController
@CrossOrigin
@RequestMapping("user-order")
@Api(tags = "用户订单相关接口")
public class UserOrderController {

    @Resource
    UserOrderService userOrderService;


    // 生成订单

    // 取消订单
}

