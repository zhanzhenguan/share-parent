package com.share.order.controller;

import java.util.List;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.log.annotation.Log;
import com.share.common.log.enums.BusinessType;
import com.share.common.security.annotation.RequiresPermissions;
import com.share.order.domain.OrderStatistics;
import com.share.order.service.IOrderStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单统计Controller
 *
 * @author share
 */
@Tag(name = "订单统计管理")
@RestController
@RequestMapping("/orderStatistics")
public class OrderStatisticsController extends BaseController
{
    @Autowired
    private IOrderStatisticsService orderStatisticsService;

    /**
     * 查询订单统计列表
     */
    @Operation(summary = "查询订单统计列表")
    @RequiresPermissions("order:orderStatistics:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderStatistics orderStatistics)
    {
        startPage();
        List<OrderStatistics> list = orderStatisticsService.selectOrderStatisticsList(orderStatistics);
        return getDataTable(list);
    }

    /**
     * 重新生成指定日期区间的订单统计
     */
    @Operation(summary = "重新生成订单统计")
    @RequiresPermissions("order:orderStatistics:refresh")
    @Log(title = "订单统计", businessType = BusinessType.UPDATE)
    @PostMapping("/refresh")
    public AjaxResult refresh(@RequestParam String orderDateBegin, @RequestParam String orderDateEnd)
    {
        return toAjax(orderStatisticsService.refreshOrderStatistics(orderDateBegin, orderDateEnd));
    }
}
