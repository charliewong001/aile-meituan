package com.pay.aile.meituan.bean.platform;

/**
 *
 * @Description: 美团订单状态
 * @see: MTOrderStatusEnum 此处填写需要参考的类
 * @version 2017年8月1日 下午5:58:25
 * @author chao.wang
 */
public enum MTOrderStatusEnum {
    submit(1, "用户已提交订单"), canPush(2, " 可推送到App方平台也可推送到商家"), shopReceived(3, " 商家已收到"), shopConfirmed(4,
            "  商家已确认"), delivering(6, "  已配送"), complete(8, " 已完成"), cancel(9, "  已取消");

    public static MTOrderStatusEnum get(int code) {
        for (MTOrderStatusEnum e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    private int code;

    private String text;

    private MTOrderStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }

}
