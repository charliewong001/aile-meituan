package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public enum PlatformCodeEnum implements Serializable {

    elm("elm", "饿了么"), mt("mt", "美团 ");
    public static PlatformCodeEnum get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static PlatformCodeEnum get(String str) {
        for (PlatformCodeEnum e : values()) {
            if (e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }

    private String code;

    private String name;

    private PlatformCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
