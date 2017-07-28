package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;

/**
 *
 * @Description: 退单推送bean
 * @see: PushRefundOrder 此处填写需要参考的类
 * @version 2017年7月27日 下午5:08:44
 * @author chao.wang
 */
public class PushRefundOrder extends PushBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 8838708578777464399L;
    private String orderId;// number 是 订单id
    private String refundStatus;// string 是 状态
    private Long updateTime;// number 是 消息发送时间
    private String code = PlatformCodeEnum.mt.getCode();

    public PushRefundOrder() {
    }

    public PushRefundOrder(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PushRefundOrder [orderId=" + orderId + ", refundStatus=" + refundStatus + ", updateTime=" + updateTime
                + ", code=" + code + ", id=" + id + "]";
    }

}
