package com.share.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.user.domain.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户登录日志Mapper接口
 *
 * @author share
 */
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog>
{
    /**
     * 查询用户登录日志列表
     *
     * @param userLoginLog 用户登录日志查询条件
     * @return 用户登录日志集合
     */
    List<UserLoginLog> selectUserLoginLogList(UserLoginLog userLoginLog);
}
