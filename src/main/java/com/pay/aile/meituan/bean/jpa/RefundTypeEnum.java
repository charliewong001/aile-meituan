package com.pay.aile.meituan.bean.jpa;

public enum RefundTypeEnum {
	
	normal("1","全额退单"),part("2","商家已确认");
	
	private String code;
	private String text;
	
	private RefundTypeEnum(String code ,String text){
		this.code =code;
		this.text =text;
	}
	
	public static RefundTypeEnum get(String str) {
		for (RefundTypeEnum e : values()) {
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
