package com.group2.dingmall.service;


import com.group2.dingmall.controller.mall.param.UserUpdateParam;
import com.group2.dingmall.controller.mall.vo.UserLoginVO;

public interface UserService {

    /**
     * 登录
     * @param loginName
     * @param passwordMD5
     * @return
     */
    UserLoginVO login(String loginName, String passwordMD5);

    /**
     * 注册
     * @param loginName
     * @param password
     */
    void register(String loginName, String password);

    /**
     * 更改用户信息（不能改loginName）
     * @param userUpdateParam
     */
    void update(UserUpdateParam userUpdateParam,long userId);
}
