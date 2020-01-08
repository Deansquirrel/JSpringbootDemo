package com.yuansong.demo.boot.jtoolstest.controller.VO;

public abstract class BaseVO {
	
	private int code;
	private String message;
	
	public BaseVO() {
		this.setCode(0);
		this.setMessage("success");
	}
	
	public BaseVO(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
