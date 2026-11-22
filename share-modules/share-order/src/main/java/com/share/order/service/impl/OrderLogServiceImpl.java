package com.share.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.utils.StringUtils;
import com.share.order.domain.OrderLog;
import com.share.order.mapper.OrderLogMapper;
import com.share.order.service.IOrderLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单操作日志Service业务层处理
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Service
public class OrderLogServiceImpl extends ServiceImpl<OrderLogMapper, OrderLog> implements IOrderLogService
{
    /**
     * 查询订单操作日志列表
     *
     * @param orderLog 订单操作日志查询条件
     * @return 订单操作日志集合
     */
    @Override
    public List<OrderLog> selectOrderLogList(OrderLog orderLog)
    {
        return baseMapper.selectOrderLogList(orderLog);
    }
}
