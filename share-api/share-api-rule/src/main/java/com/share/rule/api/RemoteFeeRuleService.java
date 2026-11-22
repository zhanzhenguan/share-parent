package com.share.rule.api;

import com.share.common.core.constant.SecurityConstants;
import com.share.common.core.constant.ServiceNameConstants;
import com.share.common.core.domain.R;
import com.share.rule.domain.FeeRule;
import com.share.rule.domain.FeeRuleRequestForm;
import com.share.rule.domain.FeeRuleResponseVo;
import com.share.rule.factory.RemoteFeeRuleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 费用规则服务
 *
 * @author share
 */
@FeignClient(contextId = "remoteFeeRuleService",
        value = ServiceNameConstants.SHARE_RULE,
        fallbackFactory = RemoteFeeRuleFallbackFactory.class)
public interface RemoteFeeRuleService
{
    /**
     * 计算订单费用
     *
     * @param feeRuleRequestForm 计费参数
     * @param source             请求来源
     * @return 费用计算结果
     */
    @PostMapping("/feeRule/calculateOrderFee")
    public R<FeeRuleResponseVo> calculateOrderFee(@RequestBody FeeRuleRequestForm feeRuleRequestForm,
                                                  @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 批量获取费用规则
     *
     * @param feeRuleIdList 规则ID集合
     * @param source        请求来源
     * @return 费用规则列表
     */
    @PostMapping(value = "/feeRule/getFeeRuleList")
    public R<List<FeeRule>> getFeeRuleList(@RequestBody List<Long> feeRuleIdList,
                                           @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 根据ID获取费用规则
     *
     * @param id     规则ID
     * @param source 请求来源
     * @return 费用规则
     */
    @GetMapping(value = "/feeRule/getFeeRule/{id}")
    public R<FeeRule> getFeeRule(@PathVariable("id") Long id,
                                 @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
