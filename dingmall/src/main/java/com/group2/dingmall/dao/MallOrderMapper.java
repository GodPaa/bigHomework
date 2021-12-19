package com.group2.dingmall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.dingmall.po.MallOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2021-12-19
 */
@Mapper
public interface MallOrderMapper extends BaseMapper<MallOrder> {

    // 添加订单
    void addOneOrder(MallOrder mallOrder);
}
