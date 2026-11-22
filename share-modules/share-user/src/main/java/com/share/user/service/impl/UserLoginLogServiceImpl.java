package com.share.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.utils.StringUtils;
import com.share.user.domain.UserLoginLog;
import com.share.user.mapper.UserLoginLogMapper;
import com.share.user.service.IUserLoginLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户登录日志Service业务层处理
 *
 * @author share
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService
{
    /**
     * 查询用户登录日志列表
     *
     * @param userLoginLog 用户登录日志查询条件
     * @return 用户登录日志集合
     */
    @Override
    public List<UserLoginLog> selectUserLoginLogList(UserLoginLog userLoginLog)
    {
        return baseMapper.selectUserLoginLogList(userLoginLog);
    }
}
