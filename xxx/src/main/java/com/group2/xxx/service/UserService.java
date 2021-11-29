package com.group2.xxx.service;


import com.group2.xxx.controller.mall.model.UserLoginModel;

public interface UserService {

    /**
     登录
     1. 参数判断，判断用户姓名、用户密码非空弄
     如果参数为空，抛出异常（异常被控制层捕获并处理）
     2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
     3. 判断用户对象是否为空
     如果对象为空，抛出异常（异常被控制层捕获并处理）
     4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
     如果密码不相等，抛出异常（异常被控制层捕获并处理）
     5. 如果密码正确，登录成功

     * @param loginName
     * @param passwordMD5
     */
    UserLoginModel login(String loginName, String passwordMD5);

}
