package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -7297686304207471885L;
    private Long id;
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
    private Double latitude;
    // 经度
    private Double longitude;
    // 店铺实收
    private BigDecimal income;
    // 订单状态
    private OrderStatusEnum status;
    // 餐盒费(饿了么)
    private BigDecimal packageFee;
    private List<OrderItem> itemList;
    // 订单状态
    private OrderRefundStatusEnum RefundStatus;
    // 催单
    private ReminderOrder reminderOrder;
    // 退单
    private RefundOrder refundOrder;

    // 配送信息
    private Distribution distribution;

    // 取消原因
    private String cancelReason;

    // 订单状态变更时间
    private Long updateTime;
    // 配送方式
    private DistributionTypeEnum distributionType;

    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Order(Long id, String orderId, OrderStatusEnum status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }

    public Order(String orderId) {
        super();
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public String getCancelReason() {
        return cancelReason;
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

    public Distribution getDistribution() {
        return distribution;
    }

    public DistributionTypeEnum getDistributionType() {
        return distributionType;
    }

    public StatusEnum getHasInvoiced() {
        return hasInvoiced;
    }

    public Long getId() {
        return id;
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
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

    public RefundOrder getRefundOrder() {
        return refundOrder;
    }

    public OrderRefundStatusEnum getRefundStatus() {
        return RefundStatus;
    }

    public ReminderOrder getReminderOrder() {
        return reminderOrder;
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
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

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public void setDistributionType(DistributionTypeEnum distributionType) {
        this.distributionType = distributionType;
    }

    public void setHasInvoiced(StatusEnum hasInvoiced) {
        this.hasInvoiced = hasInvoiced;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
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

    public void setRefundOrder(RefundOrder refundOrder) {
        this.refundOrder = refundOrder;
    }

    public void setRefundStatus(OrderRefundStatusEnum refundStatus) {
        RefundStatus = refundStatus;
    }

    public void setReminderOrder(ReminderOrder reminderOrder) {
        this.reminderOrder = reminderOrder;
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

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [id=");
        builder.append(id);
        builder.append(", shop=");
        builder.append(shop);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", address=");
        builder.append(address);
        builder.append(", orderCreateTime=");
        builder.append(orderCreateTime);
        builder.append(", deliverFee=");
        builder.append(deliverFee);
        builder.append(", deliverTime=");
        builder.append(deliverTime);
        builder.append(", description=");
        builder.append(description);
        builder.append(", hasInvoiced=");
        builder.append(hasInvoiced);
        builder.append(", invoiceTitle=");
        builder.append(invoiceTitle);
        builder.append(", onlinePaid=");
        builder.append(onlinePaid);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", daySn=");
        builder.append(daySn);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", totalPrice=");
        builder.append(totalPrice);
        builder.append(", originalPrice=");
        builder.append(originalPrice);
        builder.append(", consignee=");
        builder.append(consignee);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", income=");
        builder.append(income);
        builder.append(", status=");
        builder.append(status);
        builder.append(", packageFee=");
        builder.append(packageFee);
        builder.append(", itemList=");
        builder.append(itemList);
        builder.append(", RefundStatus=");
        builder.append(RefundStatus);
        builder.append(", reminderOrder=");
        builder.append(reminderOrder);
        builder.append(", refundOrder=");
        builder.append(refundOrder);
        builder.append(", distribution=");
        builder.append(distribution);
        builder.append(", cancelReason=");
        builder.append(cancelReason);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", distributionType=");
        builder.append(distributionType);
        builder.append("]");
        return builder.toString();
    }

}
