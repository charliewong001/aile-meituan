package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public enum OrderStatusEnum implements Serializable {

    mt_submitted("1", "用户已提交订单"), mt_can_push("2", "可推送到APP方平台也可推送到商家"), mt_Business_received("3",
            "商家已收到"), mt_business_confirmed("4", "商家已确认"), mt_delivering("6", "已配送"), mt_completed("8",
                    "已完成"), mt_cancelled("9", "已取消"), elm_pending("pending", "未生效订单"), elm_unprocessed("unprocessed",
                            "未处理订单"), elm_refunding("refunding", "退单处理中"), elm_valid("valid",
                                    "已处理的有效订单"), elm_invalid("invalid", "无效订单"), elm_settled("settled", "已完成订单");

    public static OrderStatusEnum get(String str) {
        for (OrderStatusEnum e : values()) {
            if (e.getCode().equals(str)) {
                return e;
            }
        }
        return null;
    }

    private String code;

    private String text;

    OrderStatusEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
