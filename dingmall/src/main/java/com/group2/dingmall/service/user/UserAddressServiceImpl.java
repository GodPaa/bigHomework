package com.group2.dingmall.service.user;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group2.dingmall.controller.user.param.UserAddressParam;
import com.group2.dingmall.controller.user.vo.UserAddressVO;
import com.group2.dingmall.dao.UserAddressMapper;
import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.po.User;
import com.group2.dingmall.po.UserAddress;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author lv
 * @since 2021-12-12
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 根据用户 loginName 获取用户地址
     * @param loginName
     * @return
     */
    @Override
    public UserAddressVO getUserAddress(String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 获取 userAddress
        UserAddress userAddress = userAddressMapper.selectById(userId);
        // 设置UserAddressVO
        UserAddressVO userAddressVO = new UserAddressVO();
        BeanUtil.copyProperties(userAddress,userAddressVO);
        return userAddressVO;
    }

    /**
     * 添加用户地址
     * @param loginName
     * @return
     */
    @Override
    public void addUserAddress(UserAddressParam userAddressParam, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 校验是否已经存在address
        AssertUtil.isTrue(userAddressMapper.selectOne(new QueryWrapper<UserAddress>().eq("user_id",userId)) != null,"用户地址已存在，不能重复添加");
        // 校验参数
        checkUserParam(userAddressParam);
        // 设置 userAddress
        UserAddress userAddress = new UserAddress();
        BeanUtil.copyProperties(userAddressParam,userAddress);
        userAddress.setUserId(userId);
        // 插入
        int insert = userAddressMapper.insert(userAddress);
        AssertUtil.isTrue(insert<1,"添加用户地址失败");
    }

    /**
     * 校验参数
     * @param userAddressParam
     */
    private void checkUserParam(UserAddressParam userAddressParam) {
    }
}
