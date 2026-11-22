package com.share.payment.domain;

import com.share.common.core.annotation.Excel;
import com.share.common.core.web.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Schema(description = "PaymentInfo")
public class PaymentInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "用户id")
    @Schema(description = "用户id")
    private Long userId;

    @Excel(name = "订单号")
    @Schema(description = "订单号")
    private String orderNo;

    @Excel(name = "付款方式", readConverterExp = "1=微信")
    @Schema(description = "付款方式：1-微信")
    private Integer payWay;

    @Excel(name = "交易编号")
    @Schema(description = "交易编号（微信或支付）")
    private String transactionId;

    @Excel(name = "支付金额")
    @Schema(description = "支付金额")
    private BigDecimal amount;

    @Excel(name = "交易内容")
    @Schema(description = "交易内容")
    private String content;

    @Excel(name = "支付状态", readConverterExp = "0=未支付,1=已支付,-1=关闭交易")
    @Schema(description = "支付状态：0-未支付 1-已支付 -1-关闭交易")
    private Integer paymentStatus;

    @Excel(name = "回调时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "回调时间")
    private Date callbackTime;

    @Schema(description = "回调信息")
    private String callbackContent;

}
