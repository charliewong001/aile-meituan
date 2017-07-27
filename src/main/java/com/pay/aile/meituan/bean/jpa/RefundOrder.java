package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RefundOrder implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -7087388475738725239L;
    // 订单号
    private Order order;
    // 退单状态
    private OrderRefundStatusEnum refundStatus;
    // 退单原因
    private String reason;
    // 退款类型
    private RefundTypeEnum refundType;
    // 退款总价
    private BigDecimal totalPrice;

    // 消息发送时间戳，单位秒
    private Long updateTime;

    List<RefundItem> itemList;

    public List<RefundItem> getItemList() {
        return itemList;
    }

    public Order getOrder() {
        return order;
    }

    public String getReason() {
        return reason;
    }

    public OrderRefundStatusEnum getRefundStatus() {
        return refundStatus;
    }

    public RefundTypeEnum getRefundType() {
        return refundType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setItemList(List<RefundItem> itemList) {
        this.itemList = itemList;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRefundStatus(OrderRefundStatusEnum refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setRefundType(RefundTypeEnum refundType) {
        this.refundType = refundType;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RefundOrder [order=" + order + ", refundStatus=" + refundStatus + ", reason=" + reason + ", refundType="
                + refundType + ", totalPrice=" + totalPrice + ", updateTime=" + updateTime + ", itemList=" + itemList
                + "]";
    }

}
