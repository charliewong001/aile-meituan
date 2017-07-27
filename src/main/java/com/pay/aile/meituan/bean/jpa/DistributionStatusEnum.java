package com.pay.aile.meituan.bean.jpa;

public enum DistributionStatusEnum {

    tobeAssigned("tobeAssigned", "待分配"), tobeFetched("tobeFetched", "待取餐"), delivering("delivering", "配送中"), completed(
            "completed", "已完成"), refunding("refunding", "配送成功"), cancelled("cancelled", "配送取消"), exception("exception",
                    "配送异常"), arrived("arrived", "已到店"), selfDelivery("selfDelivery",
                            "已到店"), noMoreDelivery("noMoreDelivery", "已到店"), reject("reject", "已到店");

    public static DistributionStatusEnum get(String str) {
        for (DistributionStatusEnum e : values()) {
            if (e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }

    private String code;

    private String text;

    DistributionStatusEnum(String code, String text) {
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
