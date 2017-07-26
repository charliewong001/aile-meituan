package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -9045301502245658870L;
    // 店铺id
    private Shop shop;

    // 对应订单id
    private String orderId;
    // 顾客送餐地址
    private String address;
    // 用户下单时间
    private String orderCreateTime;
    // 配送费
    private BigDecimal deliverFee;
    // 预计送达时间
    private String deliverTime;
    // 忌口或备注
    private String description;
    // 是否开发票
    private StatusEnum hasInvoiced;
    // 发票抬头
    private String invoiceTitle;
    // 是否在线支付
    private StatusEnum onlinePaid;
    // 电话号码可以多个
    private String phone;
    // 当日流水号
    private Long daySn;
    // 订单用户id
    private String userId;
    // 总价
    private BigDecimal totalPrice;
    // 订单原始价格
    private BigDecimal originalPrice;
    // 收件人姓名
    private String consignee;
    // 维度
    private double latitude;
    // 经度
    private double longitude;
    // 店铺实收
    private BigDecimal income;
    // 订单状态
    private OrderStatusEnum status;
    // 餐盒费(饿了么)
    private BigDecimal packageFee;
    private List<OrderItem> itemList;

    public Order() {
    }

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public String getConsignee() {
        return consignee;
    }

    public Long getDaySn() {
        return daySn;
    }

    public BigDecimal getDeliverFee() {
        return deliverFee;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public String getDescription() {
        return description;
    }

    public StatusEnum getHasInvoiced() {
        return hasInvoiced;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public StatusEnum getOnlinePaid() {
        return onlinePaid;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public BigDecimal getPackageFee() {
        return packageFee;
    }

    public String getPhone() {
        return phone;
    }

    public Shop getShop() {
        return shop;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setDaySn(Long daySn) {
        this.daySn = daySn;
    }

    public void setDeliverFee(BigDecimal deliverFee) {
        this.deliverFee = deliverFee;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHasInvoiced(StatusEnum hasInvoiced) {
        this.hasInvoiced = hasInvoiced;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setOnlinePaid(StatusEnum onlinePaid) {
        this.onlinePaid = onlinePaid;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPackageFee(BigDecimal packageFee) {
        this.packageFee = packageFee;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
