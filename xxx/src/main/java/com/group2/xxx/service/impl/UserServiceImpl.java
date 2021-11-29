package com.group2.xxx.service.impl;


import com.group2.xxx.dao.UserMapper;
import com.group2.xxx.controller.mall.model.UserLoginModel;
import com.group2.xxx.entity.User;
import com.group2.xxx.service.UserService;
import com.group2.xxx.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserLoginModel login(String loginName, String passwordMd5){
        /* 1. 参数判断，判断用户姓名、用户密码非空 */
        checkLoginParams(loginName,passwordMd5);

        /* 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象 */
        User user = userMapper.queryUserByName(loginName);

        /* 3. 判断用户对象是否为空 */
        AssertUtil.isTrue(user == null,"用户姓名不存在");

        /* 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码 */
        checkUserPwd(passwordMd5,user.getPasswordMd5());

        /* 5. 返回构建用户对象 */
        UserLoginModel userLoginModel = buildUserModel(user);

        return userLoginModel;
    }


    /************************   封装的方法   ************************/


    /**
     * 参数校验 (非空、合不合格式)
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        // 验证用户姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户姓名不能为空！");
        // 验证用户密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空！");
    }


    /**
     * 密码判断
     *    先将客户端传递的密码加密， 再与数据库中查询到的密码作比较
     * @param userPwd
     * @param userPwd1
     */
    private void checkUserPwd(String userPwd, String userPwd1) {
        // 将客户端传递的密码加密
//        userPwd = Md5Util.encode(userPwd);
        // 判断密码是否相等
        AssertUtil.isTrue(!userPwd.equals(userPwd1),"用户密码不正确");
    }





    /**
     * 构建需要返回给客户端的用户对象  (以后要加密)
     *       现在返回的的是 不加密的  id 密码 登录名
     *       以后可以加密id
     *       也可以只返回 token
     * @param user
     * @return
     */
    private UserLoginModel buildUserModel(User user) {
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setLoginName(user.getLoginName());
        userLoginModel.setPasswordMd5(user.getPasswordMd5());
        userLoginModel.setUserId(user.getUserId());
        return userLoginModel;
    }





}
