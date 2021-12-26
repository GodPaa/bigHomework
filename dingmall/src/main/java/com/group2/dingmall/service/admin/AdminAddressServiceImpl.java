package com.group2.dingmall.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.controller.admin.param.AddressParam;
import com.group2.dingmall.dao.admin.AdminAddressMapper;
import com.group2.dingmall.po.User;
import com.group2.dingmall.po.UserAddress;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/26
 */
@Service
public class AdminAddressServiceImpl implements AdminAddressService {
    @Resource
    private AdminAddressMapper adminAddressMapper;
    @Override
    public void addAddress(AddressParam addressParam) {
        UserAddress userAddress=new UserAddress();
        BeanUtil.copyProperties(addressParam,userAddress);
        AssertUtil.isTrue(adminAddressMapper.insert(userAddress)<1,"新增地址错误");
    }

    @Override
    public void deleteAddressById(Integer id) {
       AssertUtil.isTrue(adminAddressMapper.deleteById(id)<1,"删除失败");
    }

    @Override
    public UserAddress getAddressById(Integer userId) {
        UserAddress userAddress = adminAddressMapper.selectById(userId);
        return userAddress;
    }

    @Override
    public List<UserAddress> getAddress() {
        List<UserAddress> userAddressList=adminAddressMapper.selectList(new QueryWrapper<UserAddress>());
        return userAddressList;
    }

    @Override
    public void updateAddress(AddressParam addressParam) {
         long addressId = addressParam.getUserId();
         UserAddress userAddress= new UserAddress();
         BeanUtil.copyProperties(addressParam,userAddress);
         int update = adminAddressMapper.update(userAddress,new QueryWrapper<UserAddress>().eq("user_id",addressId));
         AssertUtil.isTrue(update<1,"修改失败");
    }
}
