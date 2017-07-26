package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public enum StatusEnum implements Serializable {

    DISENABLE(0, "禁用"), ENABLE(1, "启用");
    public static StatusEnum get(int v) {
        for (StatusEnum e : values()) {
            if (e.getStatus() == v) {
                return e;
            }
        }
        return null;
    }

    public static StatusEnum get(String str) {
        for (StatusEnum e : values()) {
            if (e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }

    private Integer status;

    private String text;

    private StatusEnum(Integer status, String text) {
        this.status = status;
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
