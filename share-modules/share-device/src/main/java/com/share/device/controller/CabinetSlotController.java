package com.share.device.controller;

import java.util.Arrays;
import java.util.List;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.log.annotation.Log;
import com.share.common.log.enums.BusinessType;
import com.share.common.security.annotation.RequiresPermissions;
import com.share.device.domain.CabinetSlot;
import com.share.device.service.ICabinetSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * 柜机插槽Controller
 *
 * @author
 * @date 2024-10-22
 */
@Tag(name = "柜机插槽接口管理")
@RestController
@RequestMapping("/cabinetSlot")
public class CabinetSlotController extends BaseController
{
    @Autowired
    private ICabinetSlotService cabinetSlotService;

    /**
     * 查询柜机插槽列表
     */
    @Operation(summary = "查询柜机插槽列表")
    @RequiresPermissions("device:cabinetSlot:list")
    @GetMapping("/list")
    public TableDataInfo list(CabinetSlot cabinetSlot)
    {
        startPage();
        List<CabinetSlot> list = cabinetSlotService.selectCabinetSlotList(cabinetSlot);
        return getDataTable(list);
    }

    /**
     * 获取柜机插槽详细信息
     */
    @Operation(summary = "获取柜机插槽详细信息")
    @RequiresPermissions("device:cabinetSlot:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cabinetSlotService.getById(id));
    }

    /**
     * 新增柜机插槽
     */
    @Operation(summary = "新增柜机插槽")
    @Log(title = "柜机插槽", businessType = BusinessType.INSERT)
    @RequiresPermissions("device:cabinetSlot:add")
    @PostMapping
    public AjaxResult add(@RequestBody CabinetSlot cabinetSlot)
    {
        return toAjax(cabinetSlotService.save(cabinetSlot));
    }

    /**
     * 修改柜机插槽
     */
    @Operation(summary = "修改柜机插槽")
    @Log(title = "柜机插槽", businessType = BusinessType.UPDATE)
    @RequiresPermissions("device:cabinetSlot:edit")
    @PutMapping
    public AjaxResult edit(@RequestBody CabinetSlot cabinetSlot)
    {
        return toAjax(cabinetSlotService.updateById(cabinetSlot));
    }

    /**
     * 删除柜机插槽
     */
    @Operation(summary = "删除柜机插槽")
    @Log(title = "柜机插槽", businessType = BusinessType.DELETE)
    @RequiresPermissions("device:cabinetSlot:remove")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cabinetSlotService.removeBatchByIds(Arrays.asList(ids)));
    }
}
