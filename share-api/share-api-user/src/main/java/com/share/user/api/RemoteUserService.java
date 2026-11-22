package com.share.user.api;

import com.share.common.core.constant.SecurityConstants;
import com.share.common.core.constant.ServiceNameConstants;
import com.share.common.core.domain.R;
import com.share.user.domain.UserInfo;
import com.share.user.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * 用户服务
 *
 * @author share
 */
@FeignClient(contextId = "remoteUserInfoService",
        value = ServiceNameConstants.SHARE_USER,
        fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 小程序授权登录
     *
     * @param code   微信登录凭证
     * @param source 请求来源
     * @return 用户信息
     */
    @GetMapping("/userInfo/wxLogin/{code}")
    public R<UserInfo> wxLogin(@PathVariable("code") String code,
                               @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 获取用户详细信息
     *
     * @param id     用户ID
     * @param source 请求来源
     * @return 用户信息
     */
    @GetMapping(value = "/userInfo/getUserInfo/{id}")
    public R<UserInfo> getInfo(@PathVariable("id") Long id,
                               @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 统计用户注册数据
     *
     * @param source 请求来源
     * @return 统计结果
     */
    @GetMapping("/userInfo/getUserCount")
    public R<Map<String, Object>> getUserCount(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
