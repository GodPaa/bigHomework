package com.group2.dingmall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
