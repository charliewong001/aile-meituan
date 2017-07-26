package com.pay.aile.meituan.bean.platform;

/**
 *
 * @Description: 美团配送单状态
 * @see: ShippingStatusEnum 此处填写需要参考的类
 * @version 2017年7月20日 上午10:35:53
 * @author chao.wang
 */
public enum ShippingStatusEnum {
    sended(0, "配送单发往配送"), shipperConfirm(10, "配送单已确认(骑手接单)"), shipperGet(20, "骑手已取餐"), shipperArrive(40,
            "骑手已送达"), cancel(100, "配送单已取消");

    public static ShippingStatusEnum get(int code) {
        for (ShippingStatusEnum e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    private int code;

    private String text;

    private ShippingStatusEnum(int code, String text) {
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
