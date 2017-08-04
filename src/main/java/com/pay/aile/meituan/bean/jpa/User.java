package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 6522699453760854116L;
    // 手机号码
    private String phone;
    // 最后修改时间
    protected Date lastUpdateTime;

    // 极光推送的id
    private String registrationId;

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
