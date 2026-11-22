package com.share.payment.controller;

import java.util.Arrays;
import java.util.List;

import com.share.common.core.utils.poi.ExcelUtil;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.log.annotation.Log;
import com.share.common.log.enums.BusinessType;
import com.share.common.security.annotation.RequiresPermissions;
import com.share.payment.domain.PaymentInfo;
import com.share.payment.service.PaymentService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * 付款信息Controller
 *
 * @author share
 */
@Tag(name = "付款信息管理")
@RestController
@RequestMapping("/paymentInfo")
public class PaymentInfoController extends BaseController
{
    @Autowired
    private PaymentService paymentService;

    /**
     * 查询付款信息列表
     */
    @Operation(summary = "查询付款信息列表")
    @RequiresPermissions("payment:paymentInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(PaymentInfo paymentInfo)
    {
        startPage();
        List<PaymentInfo> list = paymentService.selectPaymentInfoList(paymentInfo);
        return getDataTable(list);
    }

    /**
     * 导出付款信息列表
     */
    @Operation(summary = "导出付款信息列表")
    @RequiresPermissions("payment:paymentInfo:export")
    @Log(title = "付款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaymentInfo paymentInfo)
    {
        List<PaymentInfo> list = paymentService.selectPaymentInfoList(paymentInfo);
        ExcelUtil<PaymentInfo> util = new ExcelUtil<PaymentInfo>(PaymentInfo.class);
        util.exportExcel(response, list, "付款信息数据");
    }

    /**
     * 获取付款信息详细信息
     */
    @Operation(summary = "获取付款信息详细信息")
    @RequiresPermissions("payment:paymentInfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(paymentService.getById(id));
    }

    /**
     * 新增付款信息
     */
    @Operation(summary = "新增付款信息")
    @RequiresPermissions("payment:paymentInfo:add")
    @Log(title = "付款信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentInfo paymentInfo)
    {
        return toAjax(paymentService.save(paymentInfo));
    }

    /**
     * 修改付款信息
     */
    @Operation(summary = "修改付款信息")
    @RequiresPermissions("payment:paymentInfo:edit")
    @Log(title = "付款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentInfo paymentInfo)
    {
        return toAjax(paymentService.updateById(paymentInfo));
    }

    /**
     * 删除付款信息
     */
    @Operation(summary = "删除付款信息")
    @RequiresPermissions("payment:paymentInfo:remove")
    @Log(title = "付款信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(paymentService.removeBatchByIds(Arrays.asList(ids)));
    }
}
