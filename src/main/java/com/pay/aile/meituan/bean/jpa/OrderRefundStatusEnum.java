package com.pay.aile.meituan.bean.jpa;

public enum OrderRefundStatusEnum {
	
	noRefund("noRefund","未申请退单"),applied("applied","用户申请退单"),rejected("rejected","店铺拒绝退单"),arbitrating("arbitrating","客服仲裁中"),failed("failed","退单失败"),successful("successful","退单成功");

	
	private String code;
	private String text;
	
	OrderRefundStatusEnum(String code,String text){
		this.code =code;
		this.text = text;
	}

	public static OrderRefundStatusEnum get(String str) {
		for (OrderRefundStatusEnum e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getCode(){
		return code;
	}
	public String getText(){
		return text;
	}
}
