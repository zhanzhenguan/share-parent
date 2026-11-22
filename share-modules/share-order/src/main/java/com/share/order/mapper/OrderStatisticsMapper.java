package com.share.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.order.domain.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper extends BaseMapper<OrderStatistics> {

    /**
     * 查询订单统计列表
     *
     * @param orderStatistics 订单统计查询条件
     * @return 订单统计集合
     */
    List<OrderStatistics> selectOrderStatisticsList(OrderStatistics orderStatistics);

    /**
     * 删除指定日期区间的订单统计
     *
     * @param orderDateBegin 开始日期
     * @param orderDateEnd   结束日期
     * @return 删除条数
     */
    int deleteOrderStatisticsByRange(@Param("orderDateBegin") String orderDateBegin,
                                     @Param("orderDateEnd") String orderDateEnd);

    /**
     * 按订单数据重新生成指定日期区间的统计
     *
     * @param orderDateBegin 开始日期
     * @param orderDateEnd   结束日期
     * @return 新增条数
     */
    int insertOrderStatisticsByRange(@Param("orderDateBegin") String orderDateBegin,
                                     @Param("orderDateEnd") String orderDateEnd);
}
