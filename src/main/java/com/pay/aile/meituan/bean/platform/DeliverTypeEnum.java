package com.pay.aile.meituan.bean.platform;

/**
 *
 * @Description: 美团配送方式
 * @see: DeliverTypeEnum 此处填写需要参考的类
 * @version 2017年7月19日 下午4:00:28
 * @author chao.wang
 */
public enum DeliverTypeEnum {
    notThridShipping("0", "非第三方配送"), thridShipping("1", "第三方配送"), selfShipping("0000", "商家自配送"), jiamengShipping("1001",
            "美团专送-加盟"), zijianShipping("1002", "美团专送-自建"), zhongbaoShipping("1003", "美团配送-众包"), csdlShipping("1004",
                    "美团专送-城市代理"), kuaisongShipping("2002", "快送"), hunhesongShipping("3001", "美团专送－混合送");

    private String code;

    private String text;

    private DeliverTypeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public DeliverTypeEnum get(String code) {
        for (DeliverTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
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
