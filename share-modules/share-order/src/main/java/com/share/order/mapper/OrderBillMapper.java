package com.share.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.order.domain.OrderBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单账单Mapper接口
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Mapper
public interface OrderBillMapper extends BaseMapper<OrderBill>
{
    /**
     * 查询订单账单列表
     *
     * @param orderBill 订单账单查询条件
     * @return 订单账单集合
     */
    List<OrderBill> selectOrderBillList(OrderBill orderBill);
}
