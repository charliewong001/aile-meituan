package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public class Distribution implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -2897574459044075946L;
    /**
     * 订单
     */
    private Order order;
    /**
     * 店铺
     */
    private Shop shop;
    /**
     * 运单主状态
     */
    private DistributionStatusEnum status;
    /**
     * 运单子状态
     */
    private DistributionSubStatusEnum subStatus;

    /**
     * 配送员姓名
     */
    private String distributionName;

    /**
     * 配送员联系方式
     */
    private String phone;
    /**
     * 状态变更的时间戳，单位秒
     */
    private Long updateAt;

    public String getDistributionName() {
        return distributionName;
    }

    public Order getOrder() {
        return order;
    }

    public String getPhone() {
        return phone;
    }

    public Shop getShop() {
        return shop;
    }

    public DistributionStatusEnum getStatus() {
        return status;
    }

    public DistributionSubStatusEnum getSubStatus() {
        return subStatus;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setStatus(DistributionStatusEnum status) {
        this.status = status;
    }

    public void setSubStatus(DistributionSubStatusEnum subStatus) {
        this.subStatus = subStatus;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Distribution [order=" + order + ", shop=" + shop + ", status=" + status + ", subStatus=" + subStatus
                + ", distributionName=" + distributionName + ", phone=" + phone + ", updateAt=" + updateAt + "]";
    }

}
