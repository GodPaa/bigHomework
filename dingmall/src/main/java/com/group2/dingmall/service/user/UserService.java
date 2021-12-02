package com.group2.dingmall.service.user;


import com.group2.dingmall.controller.user.param.UserUpdateParam;
import com.group2.dingmall.controller.user.vo.UserInfoVO;
import com.group2.dingmall.controller.user.vo.UserLoginVO;
import com.group2.dingmall.po.User;

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

    /**
     * 用户退出 （清楚token）
     * @param userId
     */
    void logout(long userId);

    /**
     * 获取用户基本信息
     * @param user
     * @return
     */
    UserInfoVO getUserInfo(User user);
}
