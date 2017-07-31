package com.pay.aile.meituan.bean.platform;

import org.springframework.util.Assert;

public enum CancelOrderReasonEnum {
    c2001("2001", "商家超时接单"), c2002("2002", "非顾客原因修改订单"), c2003("2003", "非客户原因取消订单"), c2004("2004", "配送延迟"), c2005(
            "2005", "售后投诉"), c2006("2006", "用户要求取消"), c2007("2007", "其他原因"), c2008("2008", "店铺太忙  "), c2009("2009",
                    "商品已售完"), c2010("2010", "地址无法配送"), c2011("2011", "店铺已打烊"), c2012("2012", "联系不上用户 "), c2013("2013",
                            "重复订单"), c2014("2014", "配送员取餐慢"), c2015("2015", "配送员送餐慢"), c2016("2016", "配送员丢餐、少餐、餐洒 ");

    public static CancelOrderReasonEnum getReason(String code) {
        Assert.hasText(code, "code不能为空");
        for (CancelOrderReasonEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return c2007;
    }

    private String code;

    private String text;

    private CancelOrderReasonEnum(String code, String text) {
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
