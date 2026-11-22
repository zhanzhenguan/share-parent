package com.share.user.domain;

import com.share.common.core.annotation.Excel;
import com.share.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录日志对象 user_login_log
 *
 * @author share
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 登录IP地址 */
    @Excel(name = "登录IP地址")
    private String ipaddr;

    /** 登录状态（0成功 1失败） */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private Integer status;

    /** 提示信息 */
    @Excel(name = "提示信息")
    private String msg;
}
