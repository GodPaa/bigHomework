package com.group2.dingmall.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.group2.dingmall.controller.user.param.UserAddressParam;
import com.group2.dingmall.controller.user.vo.UserAddressVO;
import com.group2.dingmall.po.UserAddress;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author lv
 * @since 2021-12-12
 */
public interface UserAddressService{

    // 根据用户loginName获取用户地址
    UserAddressVO getUserAddress(String loginName);

    // 添加用户地址
    void addUserAddress(UserAddressParam userAddressParam, String loginName);

    // 修改用户地址
    void updateUserAddress(UserAddressParam userAddressParam, String loginName);

    // 删除用户地址
    void deleteUserAddress(String loginName);
}
