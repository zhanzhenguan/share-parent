package com.share.rules.api;

import com.share.common.core.domain.R;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.security.annotation.InnerAuth;
import com.share.rule.domain.FeeRule;
import com.share.rule.domain.FeeRuleRequestForm;
import com.share.rule.domain.FeeRuleResponseVo;
import com.share.rules.service.IFeeRuleService;
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

/**
 * 费用规则接口
 *
 * @author share
 */
@Tag(name = "费用规则接口管理")
@RestController
@RequestMapping("/feeRule")
public class FeeRuleApiController extends BaseController
{
    @Autowired
    private IFeeRuleService feeRuleService;

    /**
     * 获取全部启用的费用规则
     */
    @Operation(summary = "获取全部费用规则")
    @GetMapping("/getALLFeeRuleList")
    public AjaxResult getALLFeeRuleList()
    {
        return success(feeRuleService.getALLFeeRuleList());
    }

    /**
     * 计算订单费用（内部调用）
     */
    @Operation(summary = "计算订单费用")
    @InnerAuth
    @PostMapping("/calculateOrderFee")
    public R<FeeRuleResponseVo> calculateOrderFee(@RequestBody FeeRuleRequestForm feeRuleRequestForm) {
        return R.ok(feeRuleService.calculateOrderFee(feeRuleRequestForm));
    }

    /**
     * 批量获取费用规则（内部调用）
     */
    @Operation(summary = "批量获取费用规则")
    @InnerAuth
    @PostMapping("/getFeeRuleList")
    public R<List<FeeRule>> getFeeRuleList(@RequestBody List<Long> feeRuleIdList) {
        return R.ok(feeRuleService.listByIds(feeRuleIdList));
    }

    /**
     * 根据ID获取费用规则（内部调用）
     */
    @Operation(summary = "根据ID获取费用规则")
    @InnerAuth
    @GetMapping("/getFeeRule/{id}")
    public R<FeeRule> getFeeRule(@PathVariable("id") Long id) {
        return R.ok(feeRuleService.getById(id));
    }
}
