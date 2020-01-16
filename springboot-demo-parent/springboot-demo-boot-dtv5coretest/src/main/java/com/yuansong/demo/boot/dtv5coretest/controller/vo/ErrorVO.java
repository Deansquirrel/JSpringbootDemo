package com.yuansong.demo.boot.dtv5coretest.controller.vo;

import com.yuansong.demo.boot.dtv5coretest.global.ErrCode;

public class ErrorVO extends BaseVO {
	
	public ErrorVO() {
		super(ErrCode.FAIL.code(), ErrCode.FAIL.message());
	}
	
	public ErrorVO(Exception e) {
		super(ErrCode.FAIL.code(), e.getMessage());
	}
	
	public ErrorVO(String message) {
		super(ErrCode.FAIL.code(), message);
	}
	
}
