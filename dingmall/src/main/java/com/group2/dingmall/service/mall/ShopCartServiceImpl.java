package com.group2.dingmall.service.mall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.controller.mall.param.CartItemParam;
import com.group2.dingmall.controller.mall.vo.BookInCartVO;
import com.group2.dingmall.controller.mall.vo.BookInfoVO;
import com.group2.dingmall.dao.BookMapper;
import com.group2.dingmall.dao.ShopCartMapper;
import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.po.ShopCart;
import com.group2.dingmall.po.User;
import com.group2.dingmall.service.user.UserService;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lv
 * @Date 2021/12/12 15:55
 * @Description
 **/
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShopCartMapper shopCartMapper;

    @Resource
    private BookMapper bookMapper;

    // 添加商品
    @Override
    public void addGoodToCart(CartItemParam cartItemParam, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        System.out.println(user);
        long userId = user.getUserId();
        // 添加商品
        /* 如果book_id已经存在，追加购买 */
        ShopCart cartWithThisBook =  shopCartMapper.selectOne(new QueryWrapper<ShopCart>().eq("book_id",cartItemParam.getBookId()).eq("user_id",userId));
        if (cartWithThisBook != null){
            System.out.println("追加购买");
            int count = cartWithThisBook.getBookCount()+cartItemParam.getBookCount();
            cartWithThisBook.setBookCount(count);
            shopCartMapper.update(cartWithThisBook,new QueryWrapper<ShopCart>().eq("cart_id",cartWithThisBook.getCartId()));
        }
        else {
            /* 如果book_id不存在存在，第一次购买 */
            ShopCart shopCart = new ShopCart();
            shopCart.setUserId(userId);
            shopCart.setBookId(cartItemParam.getBookId());
            shopCart.setBookCount(cartItemParam.getBookCount());
            // 添加
            AssertUtil.isTrue(shopCartMapper.insert(shopCart) < 1,"添加失败");
            System.out.println("第一次买");
        }
    }

    // 删除购物项
    @Override
    public void deleteGoodFromCart(long bookId, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        System.out.println(user);
        long userId = user.getUserId();
        // 删除购物项
        int delete = shopCartMapper.delete(new QueryWrapper<ShopCart>().eq("book_id",bookId).eq("user_id",userId));
        AssertUtil.isTrue(delete<1,"删除失败");
    }

    // 修改购物项
    @Override
    public void updateGoodFromCart(CartItemParam cartItemParam, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 查找购物项
        ShopCart cartWithThisBook =  shopCartMapper.selectOne(new QueryWrapper<ShopCart>().eq("book_id",cartItemParam.getBookId()).eq("user_id",userId));
        // 更改
        /* 如果数量为0，直接删除 */
        if (cartItemParam.getBookCount() == 0) {
            deleteGoodFromCart(cartItemParam.getBookId(),loginName);
        }else {
            cartWithThisBook.setBookCount(cartItemParam.getBookCount());
            int update = shopCartMapper.update(cartWithThisBook,new QueryWrapper<ShopCart>().eq("cart_id",cartWithThisBook.getCartId()));
            AssertUtil.isTrue(update<1,"更改失败");
        }

    }


    // 获取购物车内的所有内容
    @Override
    public List<BookInCartVO> getShopCart(String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 获取该user的所有购物项
        List<ShopCart> cartList = shopCartMapper.selectList(new QueryWrapper<ShopCart>().eq("user_id",userId));
        // 遍历购物项，补充list其他信息
        List<BookInCartVO> list = new ArrayList<BookInCartVO>();
        for (ShopCart cart : cartList){
            long cartId = cart.getCartId();
            long bookId = cart.getBookId();
            long bookCount = cart.getBookCount();
            // 根据id找书
            Book book = bookMapper.selectById(bookId);
            BookInCartVO bookInCartVO = new BookInCartVO();
            BeanUtil.copyProperties(book,bookInCartVO);
            bookInCartVO.setCartId(cartId);
            bookInCartVO.setBookId(bookId);
            bookInCartVO.setBookCount(bookCount);
            list.add(bookInCartVO);
        }
        return list;
    }
}
