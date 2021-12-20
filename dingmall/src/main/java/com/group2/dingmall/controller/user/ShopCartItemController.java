package com.group2.dingmall.controller.user;

import com.group2.dingmall.controller.user.param.CartItemParam;
import com.group2.dingmall.controller.user.vo.BookInCartVO;
import com.group2.dingmall.service.mall.ShopCartItemService;
import com.group2.dingmall.service.user.OrderService;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/5 16:55
 * @Description
 **/
@RestController
@CrossOrigin
@RequestMapping("cartItem")
@Api(tags = "购物车界面接口")
public class ShopCartItemController {

    @Resource
    private ShopCartItemService shopCartItemService;
    @Resource
    private OrderService orderService;



    // 获得购物车清单
    @GetMapping("/book")
    @ApiOperation(value = "获取购物车商品清单，用于购物车页面", notes = "一次传过去所有数据，不分页")
    public Result getShopCart(Principal principal){
        String loginName = principal.getName();
        Result result = new Result();
        List<BookInCartVO> list = shopCartItemService.getShopCart(loginName);
        result.setData(list);
        return result;
    }


    // 添加商品到购物车
    @PostMapping("/book")
    @ApiOperation(value = "添加商品到购物车接口", notes = "传参为商品id、数量")
    public Result addGoodToCart(@RequestBody CartItemParam cartItemParam, Principal principal){
        // 参数判断
        AssertUtil.isTrue(cartItemParam.getBookId() <=0,"book_id参数错误");
        AssertUtil.isTrue(cartItemParam.getBookCount() <=0,"商品数量参数错误");
        // 获取user
        String loginName = principal.getName();
        // 操作
        Result result = new Result();
        shopCartItemService.addGoodToCart(cartItemParam,loginName);
        return result;
    }


    // 修改一件商品购物项的数量（为0则delete)
    @PutMapping("/book")
    @ApiOperation(value = "修改购物项数据", notes = "传参为购物项id、数量")
    public Result updateGoodFromCart(CartItemParam cartItemParam, Principal principal){
        System.out.println("测试十四hi是:"+cartItemParam.getBookId());
        // 参数判断
        AssertUtil.isTrue(cartItemParam.getBookId() <=0,"book_id参数错误");
        AssertUtil.isTrue(cartItemParam.getBookCount() <0,"商品数量参数错误");
        // 获取user
        String loginName = principal.getName();
        // 操作
        Result result = new Result();
        shopCartItemService.updateGoodFromCart(cartItemParam,loginName);
        return result;
    }




    // 删除
    @DeleteMapping("/book/{bookId}")
    @ApiOperation(value = "删除购物项", notes = "传参为购物项id")
    public Result deleteGoodFromCart(@PathVariable long bookId, Principal principal){
        // 参数校验
        AssertUtil.isTrue(bookId <= 0,"bookId参数错误");
        // 获取user
        String loginName = principal.getName();
        // 操作
        Result result = new Result();
        shopCartItemService.deleteGoodFromCart(bookId,loginName);
        return result;
    }


    //  结算（生成订单）
    @PostMapping("/order")
    @ApiOperation(value = "生成订单", notes = "根据用户购物车生成订单,返回订单id")
    public Result saveOrder(Principal principal){
        String loginName = principal.getName();
        long orderNo = orderService.saveOrder(loginName);
        return new Result(orderNo);
    }


}
