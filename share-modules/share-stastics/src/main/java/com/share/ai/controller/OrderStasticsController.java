package com.share.ai.controller;

import com.share.ai.service.OrderStasticsService;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.security.annotation.RequiresPermissions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单统计Controller
 *
 * @author share
 */
@Tag(name = "AI数据统计")
@RestController
@RequestMapping("/sta")
public class OrderStasticsController extends BaseController
{
    @Autowired
    private OrderStasticsService orderStasticsService;

    /**
     * 根据自然语言生成 SQL 查询订单报表数据
     */
    @Operation(summary = "AI订单数据统计")
    @RequiresPermissions("stastics:orderData:list")
    @GetMapping("/orderData")
    public AjaxResult getOrderData(@RequestParam(value = "message", defaultValue = "hello") String message)
    {
        return success(orderStasticsService.getOrderData(message));
    }
}
