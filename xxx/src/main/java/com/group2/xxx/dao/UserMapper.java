package com.group2.xxx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.xxx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
//@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     *  BaseMapper 里封装了一些常用的 CRUD
     *  直接继承 BaseMapper 可以省去xml的编写
     */



    /**
     * 也可以通过 xml 自定义 sql 查询语句
     */
    // 通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);


}
