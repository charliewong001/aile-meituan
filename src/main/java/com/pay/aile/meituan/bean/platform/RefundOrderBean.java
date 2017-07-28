package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @Description: 退款订单
 * @see: RefundOrderBean 此处填写需要参考的类
 * @version 2017年7月18日 下午2:43:20
 * @author chao.wang
 */
public class RefundOrderBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -7342656901336509730L;
    @NotNull(message = "orderId不能为空")
    private Long orderId;
    private String reason;
    @NotBlank(message = "notifyType不能为空")
    private String notifyType;

    public String getNotifyType() {
        return notifyType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RefundOrderBean [orderId=");
        builder.append(orderId);
        builder.append(", reason=");
        builder.append(reason);
        builder.append(", notifyType=");
        builder.append(notifyType);
        builder.append("]");
        return builder.toString();
    }

}
