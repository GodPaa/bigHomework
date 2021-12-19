package com.group2.dingmall.service.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.controller.user.vo.BookBriefVO;
import com.group2.dingmall.controller.user.vo.OrderInfoVO;
import com.group2.dingmall.dao.*;
import com.group2.dingmall.po.*;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
import com.group2.dingmall.utils.NumberUtil;
import com.group2.dingmall.utils.payUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2021-12-19
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private ShopCartItemMapper shopCartItemMapper;
    @Resource
    private MallOrderMapper mallOrderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private BookMapper bookMapper;


    /**
     * 根据用户购物车生成订单
     *      要动 3个表
     *          1. insert   order 订单表
     *          2. select   shop_cart_item 购物项表
     *          3. select   book 书表
     *          4. insert   order-item 联系表
     * @return
     * @param loginName
     */
    @Override
    public long saveOrder(String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();

        // 根据 userId 找到所有的购物项
        List<ShopCartItem> itemList = shopCartItemMapper.selectList(new QueryWrapper<ShopCartItem>().eq("user_id",userId));
        AssertUtil.isTrue(CollectionUtils.isEmpty(itemList),"购物车为空！");

        int totalPrice = 0;
        // 遍历购物项，统计总价格
        for (ShopCartItem item : itemList){
            int bookCount = item.getBookCount();
            long bookId = item.getBookId();
            Book book = bookMapper.selectById(bookId);
            String bookPrice = book.getOriginalPrice();
            if (bookPrice.isEmpty()) bookPrice = "20";
            int price = Integer.parseInt(Pattern.compile("[^0-9]").matcher(bookPrice).replaceAll("").trim().substring(0,2)) * bookCount;
            totalPrice = totalPrice + price;
        }

        // 新建订单
        MallOrder mallOrder = new MallOrder();
        mallOrder.setUserId(userId);
        String oderNo = NumberUtil.genOrderNo();
        mallOrder.setOrderNo(oderNo);
        mallOrder.setTotalPrice(totalPrice);
        AssertUtil.isTrue(mallOrderMapper.insert(mallOrder) != 1,"新建订单异常");


        // order-item 联系集
        mallOrder = mallOrderMapper.selectOne(new QueryWrapper<MallOrder>().eq("order_no",oderNo));
        long orderId = mallOrder.getOrderId();
        for (ShopCartItem item : itemList){
            int bookCount = item.getBookCount();
            long bookId = item.getBookId();
            Book book = bookMapper.selectById(bookId);
            String bookName = book.getBookName();
            String bookImg = book.getImgUrl();
            String bookPrice = book.getOriginalPrice();
            if (bookPrice.isEmpty()) bookPrice = "20";
            int price = Integer.parseInt(Pattern.compile("[^0-9]").matcher(bookPrice).replaceAll("").trim().substring(0,2));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setBookId(bookId);
            orderItem.setBookName(bookName);
            orderItem.setBookImg(bookImg);
            orderItem.setPrice(price);
            orderItem.setBookCount(bookCount);

            AssertUtil.isTrue(orderItemMapper.insert(orderItem) != 1,"联系集插入失败");
        }

        return orderId;
    }


    /**
     * 获取订单详情
     *      1. 校验 用户名和订单id是否对应   user 和 mall_order表
     *      2. 根据订单id获取购物项清单     order_item 表
     * @param loginName
     * @param orderId
     * @return
     */
    @Override
    public OrderInfoVO getOrderInfo(String loginName, long orderId) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();

        // 校验 用户名和订单id是否对应
        MallOrder order = mallOrderMapper.selectOne(new QueryWrapper<MallOrder>().eq("order_id",orderId).eq("user_id",userId));
        AssertUtil.isTrue(order == null,"订单不存在！(或者这个订单不属于该用户)");

        // 结果
        List<BookBriefVO> bookBriefVOList = new ArrayList<>();
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setTotalPrice(order.getTotalPrice());


        // 根据订单id获取购物项清单
        List<OrderItem> orderItemList = orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id",orderId));
        for (OrderItem item : orderItemList){
            BookBriefVO bookBriefVO = new BookBriefVO();
            BeanUtil.copyProperties(item,bookBriefVO);
            bookBriefVOList.add(bookBriefVO);
        }

        orderInfoVO.setBookBriefVOList(bookBriefVOList);
        return orderInfoVO;
    }

    /**
     *  支付
     * @param loginName
     * @param orderId
     * @return
     */
    @Override
    public String payment(String loginName, long orderId) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();

        // 校验 用户名和订单id是否对应
        MallOrder order = mallOrderMapper.selectOne(new QueryWrapper<MallOrder>().eq("order_id",orderId).eq("user_id",userId));
        AssertUtil.isTrue(order == null,"订单不存在！(或者这个订单不属于该用户)");

        // 调用阿里pay
        String orderNo = order.getOrderNo();
        int totalPrice = order.getTotalPrice();
        String js = payUtil.alipay(orderNo, String.valueOf(totalPrice), "支付宝支付", "哈哈哈哈");
        System.out.println(js);
        return js;
    }
}
