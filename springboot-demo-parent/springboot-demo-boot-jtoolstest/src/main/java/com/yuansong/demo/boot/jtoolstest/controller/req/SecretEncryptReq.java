package com.yuansong.demo.boot.jtoolstest.controller.req;

public class SecretEncryptReq {
	
	private String plainText;
	private String key;
	
	public String getPlainText() {
		return plainText;
	}
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
