package com.share.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.order.domain.OrderLog;

import java.util.List;

/**
 * 订单操作日志Service接口
 *
 * @author atguigu
 * @date 2024-10-25
 */
public interface IOrderLogService extends IService<OrderLog>
{
    /**
     * 查询订单操作日志列表
     *
     * @param orderLog 订单操作日志查询条件
     * @return 订单操作日志集合
     */
    List<OrderLog> selectOrderLogList(OrderLog orderLog);
}
