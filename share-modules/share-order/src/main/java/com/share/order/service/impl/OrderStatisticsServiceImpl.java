package com.share.order.service.impl;

import com.share.order.domain.OrderStatistics;
import com.share.order.mapper.OrderStatisticsMapper;
import com.share.order.service.IOrderStatisticsService;
import com.share.common.core.exception.ServiceException;
import com.share.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 订单统计Service业务层处理
 *
 * @author share
 */
@Service
public class OrderStatisticsServiceImpl implements IOrderStatisticsService
{
    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    /**
     * 查询订单统计列表
     *
     * @param orderStatistics 订单统计查询条件
     * @return 订单统计集合
     */
    @Override
    public List<OrderStatistics> selectOrderStatisticsList(OrderStatistics orderStatistics)
    {
        return orderStatisticsMapper.selectOrderStatisticsList(orderStatistics);
    }

    /**
     * 按订单数据重新生成指定日期区间的统计
     *
     * @param orderDateBegin 开始日期
     * @param orderDateEnd   结束日期
     * @return 生成的统计条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int refreshOrderStatistics(String orderDateBegin, String orderDateEnd)
    {
        Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        if (StringUtils.isEmpty(orderDateBegin) || StringUtils.isEmpty(orderDateEnd)
                || !datePattern.matcher(orderDateBegin).matches()
                || !datePattern.matcher(orderDateEnd).matches())
        {
            throw new ServiceException("日期格式必须为 yyyy-MM-dd");
        }
        if (orderDateBegin.compareTo(orderDateEnd) > 0)
        {
            throw new ServiceException("开始日期不能大于结束日期");
        }
        orderStatisticsMapper.deleteOrderStatisticsByRange(orderDateBegin, orderDateEnd);
        return orderStatisticsMapper.insertOrderStatisticsByRange(orderDateBegin, orderDateEnd);
    }
}
