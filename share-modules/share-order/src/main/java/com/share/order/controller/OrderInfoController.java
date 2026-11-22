package com.share.order.controller;

import java.util.Arrays;
import java.util.List;

import com.share.common.core.utils.poi.ExcelUtil;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.log.annotation.Log;
import com.share.common.log.enums.BusinessType;
import com.share.common.security.annotation.RequiresPermissions;
import com.share.order.domain.OrderInfo;
import com.share.order.service.IOrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单Controller
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController extends BaseController
{
    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 查询订单列表
     */
    @Operation(summary = "查询订单列表")
    @RequiresPermissions("order:orderInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderInfo orderInfo)
    {
        startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        return getDataTable(list);
    }

    /**
     * 订单统计图表数据（默认最近7天）
     */
    @Operation(summary = "订单统计图表数据")
    @RequiresPermissions("order:orderInfo:list")
    @GetMapping("/getOrderStatisticsData")
    public AjaxResult getOrderStatisticsData(
            @RequestParam(value = "orderDateBegin", required = false) String orderDateBegin,
            @RequestParam(value = "orderDateEnd", required = false) String orderDateEnd)
    {
        return success(orderInfoService.getOrderStatisticsData(orderDateBegin, orderDateEnd));
    }

    /**
     * 查询指定用户的订单列表
     */
    @Operation(summary = "查询用户订单列表")
    @RequiresPermissions("order:orderInfo:list")
    @GetMapping("/userList/{userId}")
    public AjaxResult userList(@PathVariable("userId") Long userId)
    {
        return success(orderInfoService.selectOrderListByUserId(userId));
    }

    /**
     * 区域订单统计图表数据（默认最近30天）
     */
    @Operation(summary = "区域订单统计图表数据")
    @RequiresPermissions("order:orderInfo:list")
    @GetMapping("/getRegionOrderStatisticsData")
    public AjaxResult getRegionOrderStatisticsData(
            @RequestParam(value = "provinceCode", required = false) String provinceCode,
            @RequestParam(value = "cityCode", required = false) String cityCode,
            @RequestParam(value = "orderDateBegin", required = false) String orderDateBegin,
            @RequestParam(value = "orderDateEnd", required = false) String orderDateEnd)
    {
        return success(orderInfoService.getRegionOrderStatisticsData(provinceCode, cityCode, orderDateBegin, orderDateEnd));
    }

    /**
     * 导出订单列表
     */
    @Operation(summary = "导出订单列表")
    @RequiresPermissions("order:orderInfo:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderInfo orderInfo)
    {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @Operation(summary = "获取订单详细信息")
    @RequiresPermissions("order:orderInfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderInfoService.selectOrderInfoById(id));
    }

    /**
     * 新增订单
     */
    @Operation(summary = "新增订单")
    @RequiresPermissions("order:orderInfo:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.save(orderInfo));
    }

    /**
     * 修改订单
     */
    @Operation(summary = "修改订单")
    @RequiresPermissions("order:orderInfo:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.updateById(orderInfo));
    }

    /**
     * 删除订单
     */
    @Operation(summary = "删除订单")
    @RequiresPermissions("order:orderInfo:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderInfoService.removeBatchByIds(Arrays.asList(ids)));
    }
}
