package com.share.rules.controller;

import com.alibaba.fastjson2.JSON;
import com.share.common.core.domain.R;
import com.share.rule.domain.FeeRule;
import com.share.rule.domain.FeeRuleRequestForm;
import com.share.rule.domain.FeeRuleResponseVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.share.common.security.annotation.RequiresPermissions;
import com.share.rules.service.IFeeRuleService;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.share.common.core.web.page.TableDataInfo;

import java.util.Arrays;
import java.util.List;

/**
 * 费用规则Controller
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Tag(name = "费用规则接口管理")
@RestController
@RequestMapping("/feeRule")
public class FeeRuleController extends BaseController
{
    @Autowired
    private IFeeRuleService feeRuleService;

    /**
     * 查询费用规则列表
     */
    @Operation(summary = "查询费用规则列表")
    @GetMapping("/list")
    public TableDataInfo list(FeeRule feeRule)
    {
        startPage();
        List<FeeRule> list = feeRuleService.selectFeeRuleList(feeRule);
        return getDataTable(list);
    }

    /**
     * 获取费用规则详细信息
     */
    @Operation(summary = "获取费用规则详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(feeRuleService.getById(id));
    }

    /**
     * 新增费用规则
     */
    @Operation(summary = "新增费用规则")
    @PostMapping
    public AjaxResult add(@RequestBody FeeRule feeRule)
    {
        return toAjax(feeRuleService.save(feeRule));
    }

    /**
     * 修改费用规则
     */
    @Operation(summary = "修改费用规则")
    @PutMapping
    public AjaxResult edit(@RequestBody FeeRule feeRule)
    {
        //return toAjax(feeRuleService.updateById(feeRule));
        System.out.println(JSON.toJSONString(feeRule));
        return toAjax(1);
    }

    /**
     * 删除费用规则
     */
    @Operation(summary = "删除费用规则")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(feeRuleService.removeBatchByIds(Arrays.asList(ids)));
    }


}

