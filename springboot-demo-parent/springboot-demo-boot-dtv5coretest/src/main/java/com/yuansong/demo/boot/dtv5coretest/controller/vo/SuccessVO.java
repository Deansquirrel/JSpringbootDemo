package com.yuansong.demo.boot.dtv5coretest.controller.vo;

import com.yuansong.demo.boot.dtv5coretest.global.ErrCode;

public class SuccessVO extends BaseVO {
	
	public SuccessVO() {
		super(ErrCode.SUCCESS.code(), ErrCode.SUCCESS.message());
	}
	
	public SuccessVO(String message) {
		super(ErrCode.SUCCESS.code(), message);
	}

}
