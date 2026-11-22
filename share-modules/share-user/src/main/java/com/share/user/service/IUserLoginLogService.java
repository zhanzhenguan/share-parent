package com.share.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.user.domain.UserLoginLog;

import java.util.List;

/**
 * 用户登录日志Service接口
 *
 * @author share
 */
public interface IUserLoginLogService extends IService<UserLoginLog>
{
    /**
     * 查询用户登录日志列表
     *
     * @param userLoginLog 用户登录日志查询条件
     * @return 用户登录日志集合
     */
    List<UserLoginLog> selectUserLoginLogList(UserLoginLog userLoginLog);
}
