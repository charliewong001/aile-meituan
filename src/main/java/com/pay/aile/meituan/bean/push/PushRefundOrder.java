package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;

public class PushRefundOrder implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 8838708578777464399L;
    private String reason;// string 是 退单原因
    private String orderId;// number 是 订单id
    private String refundStatus;// string 是 状态
    private String updateTime;// number 是 消息发送时间
    private String shopId;// number 是 店铺id
    private String platformCode = PlatformCodeEnum.mt.getCode();// string 是
                                                                // 平台code

    public String getOrderId() {
        return orderId;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public String getReason() {
        return reason;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public String getShopId() {
        return shopId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PushRefundOrder [reason=" + reason + ", orderId=" + orderId + ", refundStatus=" + refundStatus
                + ", updateTime=" + updateTime + ", shopId=" + shopId + ", platformCode=" + platformCode + "]";
    }
}
