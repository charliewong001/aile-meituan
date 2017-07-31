package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CancelOrderBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 7417342418124081986L;

    @NotNull(message = "orderId不能为空")
    private Long orderId;
    private String reason;
    private String reasonCode;

    public Long getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CancelOrderBean [orderId=");
        builder.append(orderId);
        builder.append(", reason=");
        builder.append(reason);
        builder.append(", reasonCode=");
        builder.append(reasonCode);
        builder.append("]");
        return builder.toString();
    }

}
