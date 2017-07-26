package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 *
 * @Description: 订单配送信息
 * @see: RefundOrderBean 此处填写需要参考的类
 * @version 2017年7月18日 下午2:43:20
 * @author chao.wang
 */
public class ShippingOrderBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -6232200527136637707L;
    @NotNull(message = "orderId不能为空")
    private Long orderId;// 订单ID
    @NotNull(message = "shippingStatus不能为空")
    private Integer shippingStatus;// 配送状态
    @NotNull(message = "time不能为空")
    private Long time;// 发生时间
    private String dispatcherName;// 骑手姓名
    private String dispatcherMobile;// 骑手电话

    public String getDispatcherMobile() {
        return dispatcherMobile;
    }

    public String getDispatcherName() {
        return dispatcherName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public Long getTime() {
        return time;
    }

    public void setDispatcherMobile(String dispatcherMobile) {
        this.dispatcherMobile = dispatcherMobile;
    }

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ShippingOrderBean [orderId=" + orderId + ", shippingStatus=" + shippingStatus + ", time=" + time
                + ", dispatcherName=" + dispatcherName + ", dispatcherMobile=" + dispatcherMobile + "]";
    }

}
