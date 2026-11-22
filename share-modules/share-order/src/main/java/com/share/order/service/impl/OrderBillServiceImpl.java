package com.share.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.utils.StringUtils;
import com.share.order.domain.OrderBill;
import com.share.order.mapper.OrderBillMapper;
import com.share.order.service.IOrderBillService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单账单Service业务层处理
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Service
public class OrderBillServiceImpl extends ServiceImpl<OrderBillMapper, OrderBill> implements IOrderBillService
{
    /**
     * 查询订单账单列表
     *
     * @param orderBill 订单账单查询条件
     * @return 订单账单集合
     */
    @Override
    public List<OrderBill> selectOrderBillList(OrderBill orderBill)
    {
        return baseMapper.selectOrderBillList(orderBill);
    }
}
