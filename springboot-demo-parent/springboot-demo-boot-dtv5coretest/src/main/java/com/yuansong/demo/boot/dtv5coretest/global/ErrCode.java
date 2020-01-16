package com.yuansong.demo.boot.dtv5coretest.global;

public enum ErrCode {

	SUCCESS(0, "success"),	FAIL(-1, "failed");
	
	private int value = -1;
	private String message = "";
	
	private ErrCode(int value, String message) {
		this.value = value;
		this.message = message;
	}
	
	public static ErrCode valueOfCode(int value) {
		switch(value) {
		case 0:
			return SUCCESS;
		case 1:
			return FAIL;
		default:
			return null;
		}
	}
	
	public int code() {
		return this.value;
	}
	
	public String message() {
		return this.message;
	}
	
}
