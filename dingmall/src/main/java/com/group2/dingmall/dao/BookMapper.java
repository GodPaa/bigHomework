package com.group2.dingmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lv
 * @Date 2021/12/2 10:49
 * @Description
 **/
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    
}
