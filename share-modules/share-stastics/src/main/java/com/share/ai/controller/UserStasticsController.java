package com.share.ai.controller;

import com.share.ai.service.UserStasticsService;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.security.annotation.RequiresPermissions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户统计Controller
 *
 * @author share
 */
@Tag(name = "AI数据统计")
@RestController
@RequestMapping("/sta")
public class UserStasticsController extends BaseController
{
    @Autowired
    private UserStasticsService userStasticsService;

    /**
     * 统计用户注册数据
     */
    @Operation(summary = "用户数据统计")
    @RequiresPermissions("stastics:userData:list")
    @GetMapping("/userCount")
    public AjaxResult userCount()
    {
        return success(userStasticsService.getUserCount());
    }
}
