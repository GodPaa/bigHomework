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
import java.util.Date;
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
        AssertUtil.isTrue(userAddress == null,"用户地址未设置");
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
        // 设置 userAddress
        UserAddress userAddress = new UserAddress();
        BeanUtil.copyProperties(userAddressParam,userAddress);
        userAddress.setUserId(userId);
        // 插入
        int insert = userAddressMapper.insert(userAddress);
        AssertUtil.isTrue(insert<1,"添加用户地址失败");
    }

    /**
     * 修改用户地址
     *      1.是否已经存在地址
     *          不存在：调用插入
     *          存在：
     *              2.校验参数
     *              3.修改
     *                设置修改时间
     *              4.判断是否修改成功
     * @param userAddressParam
     * @param loginName
     */
    @Override
    public void updateUserAddress(UserAddressParam userAddressParam, String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 不存在address
        UserAddress ifExist =  userAddressMapper.selectOne(new QueryWrapper<UserAddress>().eq("user_id",userId));
        if (ifExist == null){
            addUserAddress(userAddressParam,loginName);
        }
        else
        {
            // 设置参数
            UserAddress userAddress = new UserAddress();
            BeanUtil.copyProperties(userAddressParam,userAddress);
            userAddress.setUserId(userId);
            userAddress.setUpdateTime(new Date());
            // 修改
            AssertUtil.isTrue(userAddressMapper.updateById(userAddress)<1,"用户地址修改失败");
        }
    }

    /**
     * 删除当前用户的地址信息
     * @param loginName
     */
    @Override
    public void deleteUserAddress(String loginName) {
        // 获取 userId
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",loginName));
        long userId = user.getUserId();
        // 校验是否已经存在address
        AssertUtil.isTrue(userAddressMapper.selectOne(new QueryWrapper<UserAddress>().eq("user_id",userId)) == null,"用户地址还未设置，不能删除");
        // 删除操作
        AssertUtil.isTrue(userAddressMapper.deleteById(userId)<1,"用户地址删除失败");
    }
}

