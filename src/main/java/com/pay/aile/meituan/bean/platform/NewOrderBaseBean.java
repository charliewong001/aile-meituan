package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @Description: 美团推送新订单Bean
 * @see: OrderPushBean 此处填写需要参考的类
 * @version 2017年7月14日 下午4:49:01
 * @author chao.wang
 */
public class NewOrderBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 5231629409584252869L;

    @NotBlank(message = "deleloperId 不能为空")
    private Integer developerId;
    @NotBlank(message = "门店ID不能为空")
    private String ePoiId;
    @NotBlank(message = "签名不能为空")
    private String sign;

    @NotNull(message = "order不能为空")
    private NewOrderBean order;

    public Integer getDeveloperId() {
        return developerId;
    }

    public String getePoiId() {
        return ePoiId;
    }

    public NewOrderBean getOrder() {
        return order;
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

    public void setOrder(NewOrderBean order) {
        this.order = order;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "NewOrderBaseBean [developerId=" + developerId + ", ePoiId=" + ePoiId + ", sign=" + sign + ", order="
                + order + "]";
    }

}
