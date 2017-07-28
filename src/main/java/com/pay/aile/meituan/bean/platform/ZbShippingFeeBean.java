package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

/**
 *
 * @Description: 众包配送费
 * @see: ZbShippingFeeBean 此处填写需要参考的类
 * @version 2017年7月28日 下午2:21:37
 * @author chao.wang
 */
public class ZbShippingFeeBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -6959328751604896662L;
    private String orderId;// 订单号
    private String orderViewId;// 用户可见订单号
    private String shippingFee;// 配送费
    private String shippingTip;// 配送费说明

    public String getOrderId() {
        return orderId;
    }

    public String getOrderViewId() {
        return orderViewId;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public String getShippingTip() {
        return shippingTip;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderViewId(String orderViewId) {
        this.orderViewId = orderViewId;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setShippingTip(String shippingTip) {
        this.shippingTip = shippingTip;
    }

    @Override
    public String toString() {
        return "ZbShippingFeeBean [orderId=" + orderId + ", orderViewId=" + orderViewId + ", shippingFee=" + shippingFee
                + ", shippingTip=" + shippingTip + "]";
    }

}