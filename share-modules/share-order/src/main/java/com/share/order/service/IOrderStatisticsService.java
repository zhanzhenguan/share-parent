package com.share.order.service;

import com.share.order.domain.OrderStatistics;

import java.util.List;

/**
 * 订单统计Service接口
 *
 * @author share
 */
public interface IOrderStatisticsService
{
    /**
     * 查询订单统计列表
     *
     * @param orderStatistics 订单统计查询条件
     * @return 订单统计集合
     */
    List<OrderStatistics> selectOrderStatisticsList(OrderStatistics orderStatistics);

    /**
     * 按订单数据重新生成指定日期区间的统计
     *
     * @param orderDateBegin 开始日期
     * @param orderDateEnd   结束日期
     * @return 生成的统计条数
     */
    int refreshOrderStatistics(String orderDateBegin, String orderDateEnd);
}
