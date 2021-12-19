package com.group2.dingmall.service.mall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.controller.user.param.CartItemParam;
import com.group2.dingmall.controller.user.vo.BookInCartVO;
import com.group2.dingmall.dao.BookMapper;
import com.group2.dingmall.dao.ShopCartItemMapper;
import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.po.ShopCartItem;
import com.group2.dingmall.po.User;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
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
public class ShopCartItemServiceImpl implements ShopCartItemService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShopCartItemMapper shopCartItemMapper;

    @Resource
    private BookMapper bookMapper;

    // 添加商品
    @Override
    public void addGoodToCart(CartItemParam cartItemParam, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        System.out.println(user);
        long userId = user.getUserId();

        // 判断参数
        long bookId = cartItemParam.getBookId();
        Book book = bookMapper.selectById(bookId);
        AssertUtil.isTrue(book == null,"改书不存在!");


        // 添加商品
        /* 如果book_id已经存在，追加购买 */
        ShopCartItem cartWithThisBook =  shopCartItemMapper.selectOne(new QueryWrapper<ShopCartItem>().eq("book_id",cartItemParam.getBookId()).eq("user_id",userId));
        if (cartWithThisBook != null){
            System.out.println("追加购买");
            int count = cartWithThisBook.getBookCount()+cartItemParam.getBookCount();
            cartWithThisBook.setBookCount(count);
            shopCartItemMapper.update(cartWithThisBook,new QueryWrapper<ShopCartItem>().eq("cart_id",cartWithThisBook.getCartItemId()));
        }
        else {
            /* 如果book_id不存在存在，第一次购买 */
            ShopCartItem shopCartItem = new ShopCartItem();
            shopCartItem.setUserId(userId);
            shopCartItem.setBookId(cartItemParam.getBookId());
            shopCartItem.setBookCount(cartItemParam.getBookCount());
            // 添加
            AssertUtil.isTrue(shopCartItemMapper.insert(shopCartItem) < 1,"添加失败");
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
        int delete = shopCartItemMapper.delete(new QueryWrapper<ShopCartItem>().eq("book_id",bookId).eq("user_id",userId));
        AssertUtil.isTrue(delete<1,"删除失败");
    }

    // 修改购物项
    @Override
    public void updateGoodFromCart(CartItemParam cartItemParam, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 查找购物项
        ShopCartItem cartWithThisBook =  shopCartItemMapper.selectOne(new QueryWrapper<ShopCartItem>().eq("book_id",cartItemParam.getBookId()).eq("user_id",userId));
        // 更改
        /* 如果数量为0，直接删除 */
        if (cartItemParam.getBookCount() == 0) {
            deleteGoodFromCart(cartItemParam.getBookId(),loginName);
        }else {
            cartWithThisBook.setBookCount(cartItemParam.getBookCount());
            int update = shopCartItemMapper.update(cartWithThisBook,new QueryWrapper<ShopCartItem>().eq("cart_id",cartWithThisBook.getCartItemId()));
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
        List<ShopCartItem> cartList = shopCartItemMapper.selectList(new QueryWrapper<ShopCartItem>().eq("user_id",userId));
        // 遍历购物项，补充list其他信息
        List<BookInCartVO> list = new ArrayList<BookInCartVO>();
        for (ShopCartItem item : cartList){
            long cartId = item.getCartItemId();
            long bookId = item.getBookId();
            long bookCount = item.getBookCount();
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
