package com.yuansong.demo.boot.jtoolstest.controller.VO;

public class SecretDecryptVO extends BaseVO {
	
	private String plainText;
	
	public SecretDecryptVO(String plainText) {
		super();
		this.setPlainText(plainText);
	}
	
	public SecretDecryptVO(int code, String message) {
		super(code,message);
		this.setPlainText("");
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

}
