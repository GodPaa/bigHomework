package com.group2.dingmall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.UserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 收货地址表 Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2021-12-12
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {


}
