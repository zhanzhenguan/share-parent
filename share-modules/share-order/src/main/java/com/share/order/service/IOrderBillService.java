package com.share.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.order.domain.OrderBill;

import java.util.List;

/**
 * 订单账单Service接口
 *
 * @author atguigu
 * @date 2024-10-25
 */
public interface IOrderBillService extends IService<OrderBill>
{
    /**
     * 查询订单账单列表
     *
     * @param orderBill 订单账单查询条件
     * @return 订单账单集合
     */
    List<OrderBill> selectOrderBillList(OrderBill orderBill);
}
