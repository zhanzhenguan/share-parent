package com.share.user.api;

import com.share.common.core.context.SecurityContextHolder;
import com.share.common.core.domain.R;
import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.security.annotation.InnerAuth;
import com.share.common.security.annotation.RequiresLogin;
import com.share.user.domain.UserInfo;
import com.share.user.domain.UserVo;
import com.share.user.service.IUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户接口
 *
 * @author share
 */
@Tag(name = "用户接口管理")
@RestController
@RequestMapping("/userInfo")
public class UserInfoApiController extends BaseController
{
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 小程序授权登录（内部调用）
     */
    @Operation(summary = "小程序授权登录")
    @InnerAuth
    @GetMapping("/wxLogin/{code}")
    public R<UserInfo> wxLogin(@PathVariable("code") String code) {
        return R.ok(userInfoService.wxLogin(code));
    }

    /**
     * 小程序退出登录
     */
    @Operation(summary = "小程序退出登录")
    @GetMapping("/wxLogout/{code}")
    public R<UserInfo> wxLogout(@PathVariable("code") String code) {
        return R.ok(userInfoService.wxLogout(code));
    }

    /**
     * 获取当前登录用户信息
     */
    @Operation(summary = "获取当前登录用户信息")
    @RequiresLogin
    @GetMapping("/getLoginUserInfo")
    public AjaxResult getLoginUserInfo() {
        Long userId = SecurityContextHolder.getUserId();
        UserInfo userInfo = userInfoService.getById(userId);
        UserVo userInfoVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return success(userInfoVo);
    }

    /**
     * 是否免押金
     */
    @Operation(summary = "是否免押金")
    @RequiresLogin
    @GetMapping("/isFreeDeposit")
    public AjaxResult isFreeDeposit() {
        return success(userInfoService.isFreeDeposit());
    }

    /**
     * 获取用户详细信息（内部调用）
     */
    @Operation(summary = "获取用户详细信息")
    @InnerAuth
    @GetMapping(value = "/getUserInfo/{id}")
    public R<UserInfo> getInfo(@PathVariable("id") Long id) {
        return R.ok(userInfoService.getById(id));
    }

    /**
     * 统计用户注册数据（内部调用）
     */
    @Operation(summary = "统计用户注册数据")
    @InnerAuth
    @GetMapping("/getUserCount")
    public R<Map<String, Object>> getUserCount() {
        return R.ok(userInfoService.getUserCount());
    }
}
