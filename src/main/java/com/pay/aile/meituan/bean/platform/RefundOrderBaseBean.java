package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @Description:退款订单bean
 * @see: CancelOrderBaseBean 此处填写需要参考的类
 * @version 2017年7月18日 下午2:37:09
 * @author chao.wang
 */
public class RefundOrderBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -3855625307268203438L;
    @NotBlank(message = "deleloperId 不能为空")
    private Integer developerId;
    @NotBlank(message = "门店ID不能为空")
    private String ePoiId;
    @NotBlank(message = "签名不能为空")
    private String sign;

    @NotNull(message = "orderRefund不能为空")
    private RefundOrderBean orderRefund;

    public Integer getDeveloperId() {
        return developerId;
    }

    public String getePoiId() {
        return ePoiId;
    }

    public RefundOrderBean getOrderRefund() {
        return orderRefund;
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

    public void setOrderRefund(RefundOrderBean orderRefund) {
        this.orderRefund = orderRefund;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "RefundOrderBaseBean [developerId=" + developerId + ", ePoiId=" + ePoiId + ", sign=" + sign
                + ", orderRefund=" + orderRefund + "]";
    }

}
