package com.share.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.order.domain.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单Mapper接口
 *
 * @author atguigu
 * @date 2024-10-25
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo>
{

    /**
     * 查询订单列表
     *
     * @param orderInfo 订单查询条件
     * @return 订单集合
     */
    List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);

    /**
     * 按日期统计订单数量与金额
     *
     * @param orderDateBegin 开始日期
     * @param orderDateEnd   结束日期
     * @return 每日订单统计
     */
    List<Map<String, Object>> selectOrderStatisticsData(@Param("orderDateBegin") String orderDateBegin,
                                                        @Param("orderDateEnd") String orderDateEnd);

    //传递过来sql语句，根据sql语句查询数据库得到报表数据
    List<Map<String, Object>> getOrderCount(String sql);
}
