package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.User;
import com.group2.dingmall.po.UserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lv
 * @Date 2021/11/30 22:18
 * @Description
 **/
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

    UserToken selectByToken(String token);
}
