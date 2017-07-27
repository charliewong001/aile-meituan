package com.pay.aile.meituan.bean.platform;

/**
 *
 * @Description: 平台退款状态回调通知时的退款通知类型
 * @see: RefundNotifyTypeEnum 此处填写需要参考的类
 * @version 2017年7月20日 下午2:07:13
 * @author chao.wang
 */
public enum RefundNotifyTypeEnum {

    apply("apply", "发起退款"), agree("agree", "确认退款"), reject("reject", "驳回退款"), cancelRefund("cancelRefund",
            "用户取消退款申请"), cancelRefundComplaint("cancelRefundComplaint", "取消退款申诉");

    public static RefundNotifyTypeEnum getFromCode(String code) {
        for (RefundNotifyTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    private String code;
    private String text;

    private RefundNotifyTypeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }
}
