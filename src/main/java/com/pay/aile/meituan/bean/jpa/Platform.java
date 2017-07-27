package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;

public class Platform implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -3099940054791013119L;

    public static Platform getInstance() {
        return new Platform(PlatformCodeEnum.mt);
    }

    // 平台名称
    private String name;

    // 状态
    private StatusEnum status;

    private PlatformCodeEnum code;

    public Platform() {
    }

    public Platform(PlatformCodeEnum code) {
        this.code = code;
    }

    public PlatformCodeEnum getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setCode(PlatformCodeEnum code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

}
