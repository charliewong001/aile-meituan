package com.pay.aile.meituan.bean.platform;

/**
 *
 * @Description: 美团支付方式enum
 * @see: PayTypeEnum 此处填写需要参考的类
 * @version 2017年7月20日 上午11:12:32
 * @author chao.wang
 */
public enum PayTypeEnum {
    OFFLINE(1, "货到付款"), ONLINE(2, "在线支付");

    public static PayTypeEnum get(int code) {
        for (PayTypeEnum e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     * @Description 是否线上支付
     * @param code
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static boolean onlinePay(int code) {
        return ONLINE.equals(get(code));
    }

    private int code;

    private String text;

    private PayTypeEnum(int code, String text) {
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
