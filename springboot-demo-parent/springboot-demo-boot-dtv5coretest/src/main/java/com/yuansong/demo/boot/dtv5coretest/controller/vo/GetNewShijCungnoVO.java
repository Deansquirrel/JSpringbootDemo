package com.yuansong.demo.boot.dtv5coretest.controller.vo;

import com.yuansong.demo.boot.dtv5coretest.global.ErrCode;

public class GetNewShijCungnoVO extends BaseVO {
	
	private String shijCungNo = "'";
	
	public GetNewShijCungnoVO() {
		super(ErrCode.FAIL.code(), ErrCode.FAIL.message());
	}
	
	public GetNewShijCungnoVO(Exception e) {
		super(ErrCode.FAIL.code(),"获取时间存根号时遇到错误【" + e.getMessage() + "】");
	}
	
	public GetNewShijCungnoVO(String cungno) {
		super(ErrCode.SUCCESS.code(), ErrCode.SUCCESS.message());
		this.shijCungNo = cungno;
	}

	public String getShijCungNo() {
		return shijCungNo;
	}

	public void setShijCungNo(String shijCungNo) {
		this.shijCungNo = shijCungNo;
	}

}
