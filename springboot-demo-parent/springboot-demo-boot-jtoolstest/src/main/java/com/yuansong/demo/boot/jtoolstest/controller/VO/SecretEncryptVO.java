package com.yuansong.demo.boot.jtoolstest.controller.VO;

public class SecretEncryptVO extends BaseVO {

	private String cipherText;
	
	public SecretEncryptVO(String cipherText) {
		super();
		this.setCipherText(cipherText); 
	}

	public SecretEncryptVO(int code, String message) {
		super(code,message);
		this.setCipherText("");
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
}
