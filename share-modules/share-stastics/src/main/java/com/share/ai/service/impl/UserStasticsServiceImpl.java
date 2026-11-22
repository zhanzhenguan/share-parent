package com.share.ai.service.impl;

import com.share.ai.service.UserStasticsService;
import com.share.common.core.constant.SecurityConstants;
import com.share.common.core.domain.R;
import com.share.user.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户统计Service业务层处理
 *
 * @author share
 */
@Service
public class UserStasticsServiceImpl implements UserStasticsService
{
    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public Map<String, Object> getUserCount()
    {
        R<Map<String, Object>> result = remoteUserService.getUserCount(SecurityConstants.INNER);
        return result.getData();
    }
}
