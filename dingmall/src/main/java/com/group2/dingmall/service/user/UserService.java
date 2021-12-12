package com.group2.dingmall.service.user;


import com.group2.dingmall.controller.user.param.UserUpdateParam;
import com.group2.dingmall.controller.user.vo.UserInfoVO;
import com.group2.dingmall.controller.user.vo.UserLoginVO;
import com.group2.dingmall.po.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 登录
     * @param loginName
     * @param passwordMD5
     * @return
     */
    UserLoginVO login(String loginName, String passwordMD5, HttpServletRequest request);

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
    void update(UserUpdateParam userUpdateParam,String loginName);


    /**
     * 获取用户基本信息
     * @param username
     * @return
     */
    User getUserInfo(String username);



}
