package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public class ReminderOrder implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -3212935637222621651L;
    // //店铺id
    // private Shop shop;
    // 催单id
    private Long reminderId;
    // 订单id
    private Order order;

    private String reminderTime;

    private Integer reminderCount;

    public Order getOrder() {
        return order;
    }

    public Integer getReminderCount() {
        return reminderCount;
    }

    public Long getReminderId() {
        return reminderId;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setReminderCount(Integer reminderCount) {
        this.reminderCount = reminderCount;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    @Override
    public String toString() {
        return "ReminderOrder [reminderId=" + reminderId + ", order=" + order + ", reminderTime=" + reminderTime
                + ", reminderCount=" + reminderCount + "]";
    }

}
