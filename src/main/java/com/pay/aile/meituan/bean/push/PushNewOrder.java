package com.pay.aile.meituan.bean.push;

import java.io.Serializable;
import java.util.List;

import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;

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

    private String platformName = PlatformCodeEnum.mt.getName();// string 是 平台名称
                                                                // 美团
    private String platformCode = PlatformCodeEnum.mt.getCode();// string 是 平台编码
                                                                // mt
    private String consignee;// string 是 收件人 张女士
    private String address;// string 是 收件人地址 天堂
    private String phone;// string 是 收件人电话 14444444444
    private String onlinePaid;// string 是 是否在线支付 0 否 1 是
    private String hasInvoiced;// string 是 是否开发票 0 否 1 是
    private String invoiceTitle;// string 是 发票抬头 xxxxxxxxxxxxxxxxxx
    private String mealsNumber;// string 是 订餐次数 1
    private String description;// string 是 备注 不要辣
    private String daySn;// string 是 日流水号 1000
    private String orderCreateTime;// string 是 下单时间 2017-07-11 12:00:00
    private String totalPrice;// string 是 总价 60
    private String orderId;// string 是 订单编号 1200897812792015983

    private String status;// string 是 定单状态 1

    private List<PushNewOrderItem> itemList;

    public String getAddress() {
        return address;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getDaySn() {
        return daySn;
    }

    public String getDescription() {
        return description;
    }

    public String getHasInvoiced() {
        return hasInvoiced;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public List<PushNewOrderItem> getItemList() {
        return itemList;
    }

    public String getMealsNumber() {
        return mealsNumber;
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

    public String getPlatformCode() {
        return platformCode;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setDaySn(String daySn) {
        this.daySn = daySn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHasInvoiced(String hasInvoiced) {
        this.hasInvoiced = hasInvoiced;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public void setItemList(List<PushNewOrderItem> itemList) {
        this.itemList = itemList;
    }

    public void setMealsNumber(String mealsNumber) {
        this.mealsNumber = mealsNumber;
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

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PushOrder [platformName=" + platformName + ", platformCode=" + platformCode + ", consignee=" + consignee
                + ", address=" + address + ", phone=" + phone + ", onlinePaid=" + onlinePaid + ", hasInvoiced="
                + hasInvoiced + ", invoiceTitle=" + invoiceTitle + ", mealsNumber=" + mealsNumber + ", description="
                + description + ", daySn=" + daySn + ", orderCreateTime=" + orderCreateTime + ", totalPrice="
                + totalPrice + ", orderId=" + orderId + ", status=" + status + ", itemList=" + itemList == null ? "null"
                        : itemList + "]";
    }

}
