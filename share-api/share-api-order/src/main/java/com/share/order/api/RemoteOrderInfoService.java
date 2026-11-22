package com.share.order.api;

import com.share.common.core.constant.SecurityConstants;
import com.share.common.core.constant.ServiceNameConstants;
import com.share.common.core.domain.R;
import com.share.order.domain.OrderInfo;
import com.share.order.domain.OrderSqlVo;
import com.share.order.factory.RemoteOrderInfoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * 订单服务
 *
 * @author share
 */
@FeignClient(contextId = "remoteOrderInfoService",
        value = ServiceNameConstants.SHARE_ORDER,
        fallbackFactory = RemoteOrderInfoFallbackFactory.class)
public interface RemoteOrderInfoService
{
    /**
     * 根据用户ID获取未完成订单
     *
     * @param userId 用户ID
     * @param source 请求来源
     * @return 未完成订单
     */
    @GetMapping("/orderInfo/getNoFinishOrder/{userId}")
    public R<OrderInfo> getNoFinishOrder(@PathVariable("userId") Long userId,
                                         @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 根据订单号获取订单信息
     *
     * @param orderNo 订单号
     * @param source  请求来源
     * @return 订单信息
     */
    @GetMapping("/orderInfo/getByOrderNo/{orderNo}")
    public R<OrderInfo> getByOrderNo(@PathVariable("orderNo") String orderNo,
                                     @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 根据 SQL 查询订单报表数据
     *
     * @param orderSqlVo 报表 SQL
     * @param source     请求来源
     * @return 报表数据
     */
    @PostMapping("/orderInfo/getOrderCount")
    public R<Map<String, Object>> getOrderCount(@RequestBody OrderSqlVo orderSqlVo,
                                                @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
