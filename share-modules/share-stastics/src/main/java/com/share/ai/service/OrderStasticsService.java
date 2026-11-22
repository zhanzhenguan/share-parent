package com.share.ai.service;

import java.util.Map;

/**
 * 订单统计Service接口
 *
 * @author share
 */
public interface OrderStasticsService
{
    /**
     * 根据自然语言生成 SQL 并查询订单报表数据
     *
     * @param message 自然语言查询条件
     * @return 报表数据
     */
    Map<String, Object> getOrderData(String message);
}
