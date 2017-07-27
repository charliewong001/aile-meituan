package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

import com.pay.aile.meituan.bean.jpa.OrderRefundStatusEnum;
import com.pay.aile.meituan.bean.jpa.Shop;

public class PushRefundOrder implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 8838708578777464399L;
    private String reason;// string 是 退单原因
    private String orderId;// number 是 订单id
    private OrderRefundStatusEnum RefundStatus;// string 是 状态
    private Long updateTime;// number 是 消息发送时间
    private Shop shop;

    public String getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    public OrderRefundStatusEnum getRefundStatus() {
        return RefundStatus;
    }

    public Shop getShop() {
        return shop;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRefundStatus(OrderRefundStatusEnum refundStatus) {
        RefundStatus = refundStatus;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PushRefundOrder [reason=" + reason + ", orderId=" + orderId + ", RefundStatus=" + RefundStatus
                + ", updateTime=" + updateTime + ", shop=" + shop + "]";
    }

}
