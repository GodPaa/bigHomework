package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.ShopCart;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ShopCartMapper extends BaseMapper<ShopCart> {

    // 根据用户id和书id查找购物项
    ShopCart getCartById(long userId, long bookId);
}
