package com.group2.dingmall.service.user;


import com.group2.dingmall.controller.user.param.UserUpdateParam;
import com.group2.dingmall.controller.user.vo.UserInfoVO;
import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.controller.user.vo.UserLoginVO;
import com.group2.dingmall.dao.UserTokenMapper;
import com.group2.dingmall.exceptions.ParamsException;
import com.group2.dingmall.po.User;
import com.group2.dingmall.po.UserToken;
import com.group2.dingmall.service.user.UserService;
import com.group2.dingmall.utils.AssertUtil;
import com.group2.dingmall.utils.MD5Util;
import com.group2.dingmall.utils.NumberUtil;
import com.group2.dingmall.utils.SystemUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author lv
 * @Date 2021/11/8 16:36
 * @Description
 **/

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    UserTokenMapper userTokenMapper;

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

        /* 3. 判断用户对象是否被锁 */
        AssertUtil.isTrue(user.getLockedFlag() == 1,"该账号被锁，请联系管理员");

        /* 3. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码 */
        passwordMd5 = MD5Util.MD5Encode(passwordMd5, "UTF-8");                  // 将客户端传递的密码加密
        AssertUtil.isTrue(!passwordMd5.equals(user.getPasswordMd5()),"用户密码不正确");  // 判断密码是否相等

        /* 4. 返回构建用户对象 */
        UserLoginVO userLoginVO = new UserLoginVO();
        // 获取token
        String token = getToken(user);
        userLoginVO.setToken(token);
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


    /**
     * 更新用户基本信息
     * @param userUpdateParam
     * @param userId
     */
    public void update(UserUpdateParam userUpdateParam,long userId) {
        /**
         *  程序能跑到这里，说明：
         *          1. user不为null
         *          2. user没被lock
         *          3. user没被delete
         *   上面这些校验操作，检查token的时候就已经做了
         */
        User user = userMapper.selectById(userId);
        user.setNickName(userUpdateParam.getNickName());
        user.setIntroduceSign(userUpdateParam.getIntroduceSign());
        // 如果改了密码
        if (userUpdateParam.getPassword() != null){
            String passwordMd5 = MD5Util.MD5Encode(userUpdateParam.getPassword(),"UTF-8");
            user.setPasswordMd5(passwordMd5);
        }

        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1 ,"更新失败");

    }

    /**
     * 用户退出， 清楚token
     * @param userId
     */
    @Override
    public void logout(long userId) {
        userTokenMapper.deleteById(userId);
    }

    /**
     * 获取用户基本信息
     * @param user
     * @return
     */
    @Override
    public UserInfoVO getUserInfo(User user) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setNickName(user.getNickName());
        userInfoVO.setLoginName(userInfoVO.getLoginName());
        userInfoVO.setIntroduceSign(userInfoVO.getIntroduceSign());
        return userInfoVO;
    }


    /************************   封装的方法   ************************/

    /**
     * 获取 token 值
     * @param user
     * @return
     */
    private String getToken(User user) {

        /* 1. 获得token字段 */
        String timeStr = System.currentTimeMillis()+"";
        Long userId = user.getUserId();
        String src = timeStr + userId + NumberUtil.genRandomNum(4);  // 已经有token或者没有token，login后都会得到一个新的 token 字段
        String token = SystemUtil.genToken(src);

        /* 2. 设置其他字段 */
        Date now = new Date();  //当前时间
        Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时

        /* 3. 看情况 insert 或者 update 该用户的userToken */
        UserToken userToken = userTokenMapper.selectById(user.getUserId());
        // 之前没有 userToken，新增一条token数据
        if (userToken == null) {
            userToken = new UserToken();
            userToken.setUserId(user.getUserId());
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            if (userTokenMapper.insert(userToken) > 0) {
                return token;
            }

        }
        // 之前有 userToken，更新token数据
        else {
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            if (userTokenMapper.updateById(userToken) > 0) {
                return token;
            }
        }

        // 正常情况不会走到这里
        throw new ParamsException("获取token异常");
    }

}
