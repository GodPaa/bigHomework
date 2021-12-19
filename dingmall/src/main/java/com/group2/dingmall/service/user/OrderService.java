package com.group2.dingmall.service.user;


import com.group2.dingmall.controller.user.vo.OrderInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2021-12-19
 */
public interface OrderService{

    // 根据用户购物车生成订单
    long saveOrder(String loginName);

    // 根据用户名和订单id获取订单详情
    OrderInfoVO getOrderInfo(String loginName, long orderId);

    // 支付
    String payment(String loginName, long orderId);
}
