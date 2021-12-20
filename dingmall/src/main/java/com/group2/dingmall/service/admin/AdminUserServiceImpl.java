package com.group2.dingmall.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.dao.admin.AdminUserMapper;
import com.group2.dingmall.po.User;
import com.group2.dingmall.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/13
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    //增加用户
    @Override
    public void addUser(User user) {

        AssertUtil.isTrue(adminUserMapper.insert(user)<1,"添加用户错误");

    }

    //删除用户（根据ID）
    @Override
    public void deleteUserById(Long id) {

        AssertUtil.isTrue(adminUserMapper.deleteById(id)<1,"删除用户错误");

    }

    @Override
    public User getUserById(Long id) {
        User user = adminUserMapper.selectById(id);
        return user;
    }

   //查询用户列表
   @Override
    public List<User> getUser(){

        List<User> userList = adminUserMapper.selectList(new QueryWrapper<User>());

        return userList;

    }

    //修改用户信息
    @Override
    public void updateUser(User user) {

        long userId = user.getUserId();

        int upadte = adminUserMapper.update(user,new QueryWrapper<User>().eq("user_id",userId));

        AssertUtil.isTrue(upadte<1,"修改失败");

    }




}
