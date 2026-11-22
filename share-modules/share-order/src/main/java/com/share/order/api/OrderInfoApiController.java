package com.share.order.api;

import com.github.pagehelper.PageHelper;
import com.share.common.core.context.SecurityContextHolder;
import com.share.common.core.domain.R;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.security.annotation.InnerAuth;
import com.share.common.security.annotation.RequiresLogin;
import com.share.common.security.utils.SecurityUtils;
import com.share.order.domain.OrderInfo;
import com.share.order.domain.OrderSqlVo;
import com.share.order.service.IOrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订单接口
 *
 * @author atguigu
 * @date 2024-02-22
 */
@Tag(name = "订单接口管理")
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoApiController extends BaseController
{
    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 获取订单详细信息
     */
    @Operation(summary = "获取订单详细信息")
    @RequiresLogin
    @GetMapping(value = "/getOrderInfo/{id}")
    public AjaxResult getOrderInfo(@PathVariable("id") Long id)
    {
        return success(orderInfoService.selectOrderInfoById(id));
    }

    /**
     * 获取当前用户订单分页列表
     */
    @Operation(summary = "获取用户订单分页列表")
    @RequiresLogin
    @GetMapping("/userOrderInfoList/{pageNum}/{pageSize}")
    public TableDataInfo list(@PathVariable Integer pageNum,
                              @PathVariable Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderInfo> list = orderInfoService.selectOrderListByUserId(SecurityContextHolder.getUserId());
        return getDataTable(list);
    }

    /**
     * 获取当前用户未完成订单
     */
    @Operation(summary = "获取当前用户未完成订单")
    @RequiresLogin
    @GetMapping("/getNoFinishOrder")
    public AjaxResult getNoFinishOrder() {
        return success(orderInfoService.getNoFinishOrder(SecurityUtils.getUserId()));
    }

    /**
     * 根据用户ID获取未完成订单（内部调用）
     */
    @Operation(summary = "根据用户ID获取未完成订单")
    @InnerAuth
    @GetMapping("/getNoFinishOrder/{userId}")
    public R<OrderInfo> getNoFinishOrder(@PathVariable("userId") Long userId) {
        return R.ok(orderInfoService.getNoFinishOrder(userId));
    }

    /**
     * 根据订单号获取订单信息（内部调用）
     */
    @Operation(summary = "根据订单号获取订单信息")
    @InnerAuth
    @GetMapping("/getByOrderNo/{orderNo}")
    public R<OrderInfo> getByOrderNo(@PathVariable("orderNo") String orderNo) {
        return R.ok(orderInfoService.getByOrderNo(orderNo));
    }

    /**
     * 根据 SQL 查询订单报表数据（内部调用）
     */
    @Operation(summary = "根据SQL查询订单报表数据")
    @InnerAuth
    @PostMapping("/getOrderCount")
    public R<Map<String, Object>> getOrderCount(@RequestBody OrderSqlVo orderSqlVo) {
        return R.ok(orderInfoService.getOrderCount(orderSqlVo.getSql()));
    }
}
