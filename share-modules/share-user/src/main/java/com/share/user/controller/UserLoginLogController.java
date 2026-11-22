package com.share.user.controller;

import java.util.Arrays;
import java.util.List;

import com.share.common.core.utils.poi.ExcelUtil;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.log.annotation.Log;
import com.share.common.log.enums.BusinessType;
import com.share.common.security.annotation.RequiresPermissions;
import com.share.user.domain.UserLoginLog;
import com.share.user.service.IUserLoginLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录日志Controller
 *
 * @author share
 */
@Tag(name = "用户登录日志管理")
@RestController
@RequestMapping("/userLoginLog")
public class UserLoginLogController extends BaseController
{
    @Autowired
    private IUserLoginLogService userLoginLogService;

    /**
     * 查询用户登录日志列表
     */
    @Operation(summary = "查询用户登录日志列表")
    @RequiresPermissions("user:userLoginLog:list")
    @GetMapping("/list")
    public TableDataInfo list(UserLoginLog userLoginLog)
    {
        startPage();
        List<UserLoginLog> list = userLoginLogService.selectUserLoginLogList(userLoginLog);
        return getDataTable(list);
    }

    /**
     * 导出用户登录日志列表
     */
    @Operation(summary = "导出用户登录日志列表")
    @RequiresPermissions("user:userLoginLog:export")
    @Log(title = "用户登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserLoginLog userLoginLog)
    {
        List<UserLoginLog> list = userLoginLogService.selectUserLoginLogList(userLoginLog);
        ExcelUtil<UserLoginLog> util = new ExcelUtil<UserLoginLog>(UserLoginLog.class);
        util.exportExcel(response, list, "用户登录日志数据");
    }

    /**
     * 获取用户登录日志详细信息
     */
    @Operation(summary = "获取用户登录日志详细信息")
    @RequiresPermissions("user:userLoginLog:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userLoginLogService.getById(id));
    }

    /**
     * 删除用户登录日志
     */
    @Operation(summary = "删除用户登录日志")
    @RequiresPermissions("user:userLoginLog:remove")
    @Log(title = "用户登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userLoginLogService.removeBatchByIds(Arrays.asList(ids)));
    }
}
