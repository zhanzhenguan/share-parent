package com.share.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.payment.domain.PaymentInfo;

import java.util.List;

public interface PaymentInfoMapper extends BaseMapper<PaymentInfo> {

    /**
     * 查询付款信息列表
     *
     * @param paymentInfo 付款信息查询条件
     * @return 付款信息集合
     */
    List<PaymentInfo> selectPaymentInfoList(PaymentInfo paymentInfo);
}
