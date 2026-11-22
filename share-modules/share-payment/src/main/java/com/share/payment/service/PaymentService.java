package com.share.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.payment.domain.PaymentInfo;
import com.wechat.pay.java.service.payments.model.Transaction;

import java.util.List;

public interface PaymentService extends IService<PaymentInfo> {
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Transaction transaction);

    /**
     * 查询付款信息列表
     *
     * @param paymentInfo 付款信息查询条件
     * @return 付款信息集合
     */
    List<PaymentInfo> selectPaymentInfoList(PaymentInfo paymentInfo);
}
