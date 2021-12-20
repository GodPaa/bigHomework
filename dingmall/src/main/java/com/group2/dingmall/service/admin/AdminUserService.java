package com.group2.dingmall.service.admin;

import com.group2.dingmall.po.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2021/12/14
 */
@Service
public interface AdminUserService {
    //添加用户
    void addUser(User user);
    //删除用户（根据ID）
    void deleteUserById(Long id);
    //获取用户列表
    List<User> getUser();
    //修改用户信息（根据ID）
    void updateUser(User user);
    //根据用户ID查询书本信息
    User getUserById(Long id);
}
