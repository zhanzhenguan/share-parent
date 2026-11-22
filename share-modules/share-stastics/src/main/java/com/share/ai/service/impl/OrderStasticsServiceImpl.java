package com.share.ai.service.impl;

import com.share.ai.service.OrderStasticsService;
import com.share.common.core.constant.SecurityConstants;
import com.share.common.core.domain.R;
import com.share.common.core.exception.ServiceException;
import com.share.common.core.utils.SqlUtils;
import com.share.order.api.RemoteOrderInfoService;
import com.share.order.domain.OrderSqlVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 订单统计Service业务层处理
 *
 * @author share
 */
@Slf4j
@Service
public class OrderStasticsServiceImpl implements OrderStasticsService
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RemoteOrderInfoService remoteOrderInfoService;

    /**
     * AI 服务地址，可在 Nacos 配置中覆盖
     */
    @Value("${ai.generate-sql-url:http://localhost:8899/ai/generateSql}")
    private String generateSqlUrl;

    @Override
    public Map<String, Object> getOrderData(String message)
    {
        //调用 AI 服务，根据自然语言生成 SQL
        String sql = restTemplate.getForObject(generateSqlUrl + "?message={message}", String.class, message);
        log.info("AI生成的SQL语句：{}", sql);

        //只允许执行安全的单条查询语句，防止提示词注入导致数据被篡改
        if (!SqlUtils.isSafeSelect(sql))
        {
            log.warn("AI生成的SQL不合法，拒绝执行：{}", sql);
            throw new ServiceException("AI生成的SQL语句不合法，仅支持单条查询语句");
        }

        //远程调用订单服务，执行 SQL 获取报表数据
        OrderSqlVo orderSqlVo = new OrderSqlVo();
        orderSqlVo.setSql(sql);
        R<Map<String, Object>> result = remoteOrderInfoService.getOrderCount(orderSqlVo, SecurityConstants.INNER);
        return result.getData();
    }
}
