package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

public class RefundItem implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 6352141122786728884L;

    // 退单
    private RefundOrder refundOrder;

    //
    private String name;

    private Integer quantity;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public RefundOrder getRefundOrder() {
        return refundOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setRefundOrder(RefundOrder refundOrder) {
        this.refundOrder = refundOrder;
    }

    @Override
    public String toString() {
        return "RefundItem [refundOrder=" + refundOrder + ", name=" + name + ", quantity=" + quantity + ", price="
                + price + "]";
    }

}
