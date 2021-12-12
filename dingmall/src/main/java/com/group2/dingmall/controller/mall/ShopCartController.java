package com.group2.dingmall.controller.mall;

import com.group2.dingmall.controller.mall.param.CartItemParam;
import com.group2.dingmall.controller.mall.vo.BookInCartVO;
import com.group2.dingmall.po.ShopCart;
import com.group2.dingmall.service.mall.ShopCartService;
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
@RequestMapping("cart")
@Api(tags = "购物车相关接口")
public class ShopCartController {
    @Resource
    private ShopCartService shopCartService;


    // 获得购物车清单
    @GetMapping("/book")
    @ApiOperation(value = "购物车列表", notes = "一次传过去所有数据，不分页")
    public Result getShopCart(Principal principal){
        String loginName = principal.getName();
        Result result = new Result();
        List<BookInCartVO> list = shopCartService.getShopCart(loginName);
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
        shopCartService.addGoodToCart(cartItemParam,loginName);
        return result;
    }


    // 修改购物车中某个商品的数量
    @PutMapping("/book")
    @ApiOperation(value = "修改购物项数据", notes = "传参为购物项id、数量")
    public Result updateGoodFromCart(@RequestBody CartItemParam cartItemParam, Principal principal){
        // 参数判断
        AssertUtil.isTrue(cartItemParam.getBookId() <=0,"book_id参数错误");
        AssertUtil.isTrue(cartItemParam.getBookCount() <0,"商品数量参数错误");
        // 获取user
        String loginName = principal.getName();
        // 操作
        Result result = new Result();
        shopCartService.updateGoodFromCart(cartItemParam,loginName);
        return result;
    }




    // 从购物车中删除一件商品
    @DeleteMapping("/book/{bookId}")
    @ApiOperation(value = "删除购物项", notes = "传参为购物项id")
    public Result deleteGoodFromCart(@PathVariable long bookId, Principal principal){
        // 参数校验
        AssertUtil.isTrue(bookId <= 0,"bookId参数错误");
        // 获取user
        String loginName = principal.getName();
        // 操作
        Result result = new Result();
        shopCartService.deleteGoodFromCart(bookId,loginName);
        return result;
    }





}
