package com.share.ai.service;

import java.util.Map;

/**
 * 用户统计Service接口
 *
 * @author share
 */
public interface UserStasticsService
{
    /**
     * 统计用户注册数据
     *
     * @return 统计结果
     */
    Map<String, Object> getUserCount();
}
