package com.group2.dingmall.service.admin;

import com.group2.dingmall.controller.admin.param.AddressParam;
import com.group2.dingmall.po.UserAddress;

import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/26
 */
public interface AdminAddressService {

    //增加用户地址
    void addAddress(AddressParam addressParam);
    //删除用户地址
    void deleteAddressById(Integer id);
    //根据用户ID查询用户地址列表
    UserAddress getAddressById(Integer userId);
    //查询所有用户地址
    List<UserAddress> getAddress();
    //更新用户地址信息
    void updateAddress(AddressParam addressParam);
}
