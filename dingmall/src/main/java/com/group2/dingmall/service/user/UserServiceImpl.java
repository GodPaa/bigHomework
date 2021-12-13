package com.group2.dingmall.service.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.config.security.JwtTokenUtil;
import com.group2.dingmall.controller.user.param.UserUpdateParam;
import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.controller.user.vo.UserLoginVO;
import com.group2.dingmall.po.User;
import com.group2.dingmall.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author lv
 * @Date 2021/11/8 16:36
 * @Description
 **/

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
       登录
     * @param loginName
     * @param passwordMd5
     */
    public UserLoginVO login(String loginName, String passwordMd5, String code, HttpServletRequest request){
        // 校验验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
//        AssertUtil.isTrue(!captcha.equalsIgnoreCase(code),"验证码不正确");
        // 登录
        UserDetails userDetails = loadUserByUsername(loginName);
        AssertUtil.isTrue(null == userDetails || !passwordEncoder.matches(passwordMd5,userDetails.getPassword()),"用户名或密码不正确");
        AssertUtil.isTrue(!userDetails.isEnabled(),"账号被禁用,请联系管理员！");

        // 更新登录()
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setToken(token);
        userLoginVO.setTokenHead(tokenHead);
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
        user.setPasswordMd5(passwordEncoder.encode(password));
        userMapper.insert(user);
    }


    /**
     * 更新用户基本信息
     * @param userUpdateParam
     * @param loginName
     */
    public void update(UserUpdateParam userUpdateParam,String loginName) {
        /**
         *  程序能跑到这里，说明：
         *          1. user不为null
         *          2. user没被lock
         *          3. user没被delete
         *   上面这些校验操作，检查token的时候就已经做了
         */
        User user = userMapper.queryUserByName(loginName);
        user.setNickName(userUpdateParam.getNickName());
        user.setIntroduceSign(userUpdateParam.getIntroduceSign());
        // 如果改了密码
        System.out.println(userUpdateParam.getPassword());

        if (userUpdateParam.getPassword() != null){
            String passwordMd5 = passwordEncoder.encode(userUpdateParam.getPassword());
            user.setPasswordMd5(passwordMd5);
            System.out.println(passwordMd5);
        }

        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1 ,"更新失败");

    }


    /**
     * 获取用户基本信息
     * @param username
     * @return
     */
    @Override
    public User getUserInfo(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",username));
        return user;
    }



    /************************   封装的方法   ************************/

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        UserDetails userDetails = userMapper.queryUserByName(loginName);
        AssertUtil.isTrue(userDetails == null,"不存在该用户");
        return userDetails;
    }
}
