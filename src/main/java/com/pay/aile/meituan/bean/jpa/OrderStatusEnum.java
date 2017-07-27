package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public enum OrderStatusEnum implements Serializable {

    unprocessed("unprocessed", "未处理订单"), confirmed("confirmed", "商家已确认"), cancelled("cancelled", "已取消"), valid("valid",
            "处理中的订单"), completed("completed", "已完成"), refunding("refunding",
                    "退单处理中"), refunded("refunded", "退单完成"), invalid("invalid", "取消后的无效订单");
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
