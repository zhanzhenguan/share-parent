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
import com.share.order.domain.OrderLog;
import com.share.order.service.IOrderLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单操作日志Controller
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Tag(name = "订单操作日志管理")
@RestController
@RequestMapping("/orderLog")
public class OrderLogController extends BaseController
{
    @Autowired
    private IOrderLogService orderLogService;

    /**
     * 查询订单操作日志列表
     */
    @Operation(summary = "查询订单操作日志列表")
    @RequiresPermissions("order:orderLog:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderLog orderLog)
    {
        startPage();
        List<OrderLog> list = orderLogService.selectOrderLogList(orderLog);
        return getDataTable(list);
    }

    /**
     * 导出订单操作日志列表
     */
    @Operation(summary = "导出订单操作日志列表")
    @RequiresPermissions("order:orderLog:export")
    @Log(title = "订单操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderLog orderLog)
    {
        List<OrderLog> list = orderLogService.selectOrderLogList(orderLog);
        ExcelUtil<OrderLog> util = new ExcelUtil<OrderLog>(OrderLog.class);
        util.exportExcel(response, list, "订单操作日志数据");
    }

    /**
     * 获取订单操作日志详细信息
     */
    @Operation(summary = "获取订单操作日志详细信息")
    @RequiresPermissions("order:orderLog:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderLogService.getById(id));
    }

    /**
     * 新增订单操作日志
     */
    @Operation(summary = "新增订单操作日志")
    @RequiresPermissions("order:orderLog:add")
    @Log(title = "订单操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderLog orderLog)
    {
        return toAjax(orderLogService.save(orderLog));
    }

    /**
     * 删除订单操作日志
     */
    @Operation(summary = "删除订单操作日志")
    @RequiresPermissions("order:orderLog:remove")
    @Log(title = "订单操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderLogService.removeBatchByIds(Arrays.asList(ids)));
    }
}
