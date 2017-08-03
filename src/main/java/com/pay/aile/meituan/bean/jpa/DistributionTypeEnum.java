package com.pay.aile.meituan.bean.jpa;

/**
 *
 * @Description: 配送方式
 * @see: DistributionTypeEnum 此处填写需要参考的类
 * @version 2017年8月3日 上午11:24:44
 * @author chao.wang
 */
public enum DistributionTypeEnum {
    platform("platform", "平台配送"), shop("shop", "店铺自行配送"), other("other", "第三方配送");

    private String code;

    private String text;

    private DistributionTypeEnum(String code, String text) {
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
