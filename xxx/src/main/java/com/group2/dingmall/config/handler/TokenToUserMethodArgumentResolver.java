
package com.group2.dingmall.config.handler;

import com.group2.dingmall.config.annotation.TokenToUser;
import com.group2.dingmall.dao.UserMapper;
import com.group2.dingmall.dao.UserTokenMapper;
import com.group2.dingmall.po.User;
import com.group2.dingmall.po.UserToken;
import com.group2.dingmall.utils.AssertUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

@Component
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserTokenMapper userTokenMapper;

    public TokenToUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            User user = null;
            String token = webRequest.getHeader("token");

            System.out.println("已处理");

            /* 检查token是否有效 */
            AssertUtil.isTrue(null== token || "".equals(token),"token为空");
            UserToken userToken = userTokenMapper.selectByToken(token);
            AssertUtil.isTrue(userToken == null || userToken.getExpireTime().getTime() <= System.currentTimeMillis(),"token无效或超过有效时间");

            /* 检查token对应的user是否有效 */
            user = userMapper.selectById(userToken.getUserId());
            AssertUtil.isTrue(user == null,"");   // 该user对应的user是否为空（可能这条记录被彻底删了）
            AssertUtil.isTrue(user.getLockedFlag() == 1,"你被锁了，请联系管理员");   // 被锁了
            AssertUtil.isTrue(user.getIsDeleted()  == 1,"你被删了，请联系管理员");   // 被删了

            return user;
        }
        return null;
    }



}
