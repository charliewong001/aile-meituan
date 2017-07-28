package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @Description:取消订单bean
 * @see: CancelOrderBaseBean 此处填写需要参考的类
 * @version 2017年7月18日 下午2:37:09
 * @author chao.wang
 */
public class CancelOrderBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 5662701595637316007L;

    @NotBlank(message = "deleloperId 不能为空")
    private Integer developerId;
    @NotBlank(message = "门店ID不能为空")
    private String ePoiId;

    @NotBlank(message = "签名不能为空")
    private String sign;

    @NotNull(message = "orderCancel不能为空")
    private CancelOrderBean orderCancel;

    public Integer getDeveloperId() {
        return developerId;
    }

    public String getePoiId() {
        return ePoiId;
    }

    public CancelOrderBean getOrderCancel() {
        return orderCancel;
    }

    public String getSign() {
        return sign;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public void setePoiId(String ePoiId) {
        this.ePoiId = ePoiId;
    }

    public void setOrderCancel(CancelOrderBean orderCancel) {
        this.orderCancel = orderCancel;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "CancelOrderBaseBean [developerId=" + developerId + ", ePoiId=" + ePoiId + ", sign=" + sign
                + ", orderCancel=" + orderCancel + "]";
    }

}
