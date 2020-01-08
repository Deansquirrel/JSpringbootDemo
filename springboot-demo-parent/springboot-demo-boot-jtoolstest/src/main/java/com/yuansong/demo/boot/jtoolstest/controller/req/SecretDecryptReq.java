package com.yuansong.demo.boot.jtoolstest.controller.req;

public class SecretDecryptReq {
	
	private String cipherText;
	private String key;
	
	public String getCipherText() {
		return cipherText;
	}
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
