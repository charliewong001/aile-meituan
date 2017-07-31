package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

public class Shop implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -4316569972108157426L;
    // 商户对应平台id
    private String shopId;
    // 商户名称
    private String shopName;
    // 地址
    private String address;
    // 纬度
    private String latitude;
    // 经度
    private String longitude;

    // 电话号码 可以录入多个
    private String phone;

    // 店铺默认配送费
    private BigDecimal shippingFee;
    // 营业时间
    private String businessHours;

    // 门店的营业状态
    private String openLevel;
    // 是否上线
    private String isOnline;
    // 门店是否支持发票
    private String invoiceSupport;
    // 支持的最小发票金额
    private BigDecimal invoiceMinAmount;
    // 店铺绑定的外部ID
    private String openId;

    // 预定时间
    private String preBook;
    // 平台
    private Platform platform;
    // 配送范围
    private String deliverGeoJson;
    // 设备id
    private String deviceNo;
    // 商户编号
    private String customerNo;
    // 服务商编号
    private String agents;
    // 自动接单状态
    private StatusEnum automaticStatus;
    // 极光推送id
    private String registrationId;

    public Shop() {
    }

    public Shop(String shopId) {
        this.shopId = shopId;
    }

    public Shop(String shopId, Platform platform) {
        this.shopId = shopId;
        this.platform = platform;
    }

    public String getAddress() {
        return address;
    }

    public String getAgents() {
        return agents;
    }

    public StatusEnum getAutomaticStatus() {
        return automaticStatus;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public String getDeliverGeoJson() {
        return deliverGeoJson;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public BigDecimal getInvoiceMinAmount() {
        return invoiceMinAmount;
    }

    public String getInvoiceSupport() {
        return invoiceSupport;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getOpenId() {
        return openId;
    }

    public String getOpenLevel() {
        return openLevel;
    }

    public String getPhone() {
        return phone;
    }

    public Platform getPlatform() {
        return platform;
    }

    public String getPreBook() {
        return preBook;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAgents(String agents) {
        this.agents = agents;
    }

    public void setAutomaticStatus(StatusEnum automaticStatus) {
        this.automaticStatus = automaticStatus;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public void setDeliverGeoJson(String deliverGeoJson) {
        this.deliverGeoJson = deliverGeoJson;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public void setInvoiceMinAmount(BigDecimal invoiceMinAmount) {
        this.invoiceMinAmount = invoiceMinAmount;
    }

    public void setInvoiceSupport(String invoiceSupport) {
        this.invoiceSupport = invoiceSupport;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setOpenLevel(String openLevel) {
        this.openLevel = openLevel;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public void setPreBook(String preBook) {
        this.preBook = preBook;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shop [shopId=");
        builder.append(shopId);
        builder.append(", shopName=");
        builder.append(shopName);
        builder.append(", address=");
        builder.append(address);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", shippingFee=");
        builder.append(shippingFee);
        builder.append(", businessHours=");
        builder.append(businessHours);
        builder.append(", openLevel=");
        builder.append(openLevel);
        builder.append(", isOnline=");
        builder.append(isOnline);
        builder.append(", invoiceSupport=");
        builder.append(invoiceSupport);
        builder.append(", invoiceMinAmount=");
        builder.append(invoiceMinAmount);
        builder.append(", openId=");
        builder.append(openId);
        builder.append(", preBook=");
        builder.append(preBook);
        builder.append(", platform=");
        builder.append(platform);
        builder.append(", deliverGeoJson=");
        builder.append(deliverGeoJson);
        builder.append(", deviceNo=");
        builder.append(deviceNo);
        builder.append(", customerNo=");
        builder.append(customerNo);
        builder.append(", agents=");
        builder.append(agents);
        builder.append(", automaticStatus=");
        builder.append(automaticStatus);
        builder.append(", registrationId=");
        builder.append(registrationId);
        builder.append("]");
        return builder.toString();
    }

}
