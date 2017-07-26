package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

/**
 *
 * @Description: 向POS端推送的bean
 * @see: PushOrder 此处填写需要参考的类
 * @version 2017年7月19日 下午4:34:07
 * @author chao.wang
 */
public class PushNewOrder implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 6842923146556312863L;

    private String serialNumber;
    private String deliverTime;
    private String onlinePaid;
    private String code;
    private String consignee;
    private String address;
    private String phone;
    private String orderId;
    private String orderCreateTime;
    private Long id;

    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public Long getId() {
        return id;
    }

    public String getOnlinePaid() {
        return onlinePaid;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPhone() {
        return phone;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOnlinePaid(String onlinePaid) {
        this.onlinePaid = onlinePaid;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "PushNewOrder [serialNumber=" + serialNumber + ", deliverTime="
                + deliverTime + ", onlinePaid=" + onlinePaid + ", code=" + code
                + ", consignee=" + consignee + ", address=" + address
                + ", phone=" + phone + ", orderId=" + orderId
                + ", orderCreateTime=" + orderCreateTime + ", id=" + id + "]";
    }

}
