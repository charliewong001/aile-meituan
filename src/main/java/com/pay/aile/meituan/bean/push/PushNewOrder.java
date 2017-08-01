package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

import com.pay.aile.meituan.bean.jpa.OrderStatusEnum;
import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;

/**
 *
 * @Description: 向POS端推送的bean
 * @see: PushOrder 此处填写需要参考的类
 * @version 2017年7月19日 下午4:34:07
 * @author chao.wang
 */
public class PushNewOrder extends PushBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 6842923146556312863L;

    private String deliverTime;
    private String onlinePaid;
    private String code = PlatformCodeEnum.mt.getCode();
    private String consignee;
    private String address;

    private String phone;
    private String orderId;
    private String orderCreateTime;
    private Long updateTime;
    private String shopId;

    public PushNewOrder() {
    }

    public PushNewOrder(Long id) {
        this.id = id;
        status = OrderStatusEnum.unprocessed.getCode();
    }

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

    public String getShopId() {
        return shopId;
    }

    public Long getUpdateTime() {
        return updateTime;
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

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PushNewOrder [deliverTime=");
        builder.append(deliverTime);
        builder.append(", onlinePaid=");
        builder.append(onlinePaid);
        builder.append(", code=");
        builder.append(code);
        builder.append(", consignee=");
        builder.append(consignee);
        builder.append(", address=");
        builder.append(address);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", orderCreateTime=");
        builder.append(orderCreateTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", shopId=");
        builder.append(shopId);
        builder.append(", id=");
        builder.append(id);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }

}
