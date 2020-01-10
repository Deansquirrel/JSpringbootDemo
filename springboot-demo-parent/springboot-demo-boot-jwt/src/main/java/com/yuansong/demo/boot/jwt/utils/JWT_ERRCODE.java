package com.yuansong.demo.boot.jwt.utils;

public enum JWT_ERRCODE {
	
	SUCCESS(0),	FAIL(1);
	
	private int value = -1;
	
	private JWT_ERRCODE(int value) {
		this.value = value;
	}
	
	public static JWT_ERRCODE valueOf(int value) {
		switch(value) {
		case 0:
			return SUCCESS;
		case 1:
			return FAIL;
		default:
			return null;
		}
	}
	
	public int value() {
		return this.value;
	}
}
