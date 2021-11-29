package com.group2.dingmall.service.impl;


import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.controller.mall.vo.UserLoginVO;
import com.group2.dingmall.po.User;
import com.group2.dingmall.service.UserService;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sun.nio.cs.US_ASCII;

import javax.annotation.Resource;

/**
 * @Author lv
 * @Date 2021/11/8 16:36
 * @Description
 **/

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
       登录
     * @param loginName
     * @param passwordMd5
     */
    public UserLoginVO login(String loginName, String passwordMd5){

        /* 1. 调用数据访问层，通过用户名查询用户记录，返回用户对象 */
        User user = userMapper.queryUserByName(loginName);

        /* 2. 判断用户对象是否为空 */
        AssertUtil.isTrue(user == null,"用户姓名不存在");

        /* 3. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码 */
        passwordMd5 = MD5Util.MD5Encode(passwordMd5, "UTF-8");                  // 将客户端传递的密码加密
        AssertUtil.isTrue(!passwordMd5.equals(user.getPasswordMd5()),"用户密码不正确");  // 判断密码是否相等

        /* 4. 返回构建用户对象 */
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setLoginName(user.getLoginName());
        String userIdMd5 = MD5Util.MD5Encode(user.getUserId()+"","UTF-8");
        userLoginVO.setUserIdMd5(userIdMd5);

        return userLoginVO;
    }

    /**
     * 注册
     * @param loginName
     * @param password
     */
    @Override
    public void register(String loginName, String password) {
        /* 检查登录名是否已经存在 */
        AssertUtil.isTrue(userMapper.queryUserByName(loginName) != null,"该用户名已被注册！");

        User user = new User();
        user.setLoginName(loginName);
        user.setPasswordMd5(MD5Util.MD5Encode(password,"UTF-8"));
        userMapper.insert(user);
    }


}
