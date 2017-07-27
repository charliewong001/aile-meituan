package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;

public class PushCancelOrder implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -951741652438254127L;
    private String orderId;// number 是 订单id
    private Long updateTime;// number 是 消息发送时间
    private String code = PlatformCodeEnum.mt.getCode();

    public String getCode() {
        return code;
    }

    public String getOrderId() {
        return orderId;
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

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PushCancelOrder [orderId=" + orderId + ", updateTime=" + updateTime + ", code=" + code + "]";
    }

}
