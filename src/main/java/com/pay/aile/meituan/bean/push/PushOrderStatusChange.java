package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;

/**
 *
 * @Description: 订单状态变更推送bean
 * @see: PushOrderStatusChange 此处填写需要参考的类
 * @version 2017年7月27日 下午1:45:57
 * @author chao.wang
 */
public class PushOrderStatusChange extends PushBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 5397559622712985948L;

    private Long orderId;
    private String status;
    private String code = PlatformCodeEnum.mt.getCode();
    private Long updateTime;

    public PushOrderStatusChange() {
    }

    public PushOrderStatusChange(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PushOrderStatusChange [orderId=" + orderId + ", status=" + status + ", code=" + code + ", updateTime="
                + updateTime + ", id=" + id + "]";
    }

}
