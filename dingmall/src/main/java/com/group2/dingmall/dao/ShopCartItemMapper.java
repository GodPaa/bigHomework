package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.ShopCartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ShopCartItemMapper extends BaseMapper<ShopCartItem> {

    // 根据用户id和书id查找购物项
    ShopCartItem getCartById(long userId, long bookId);


}
