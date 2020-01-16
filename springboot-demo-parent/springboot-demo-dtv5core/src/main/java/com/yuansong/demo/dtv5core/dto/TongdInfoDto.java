package com.yuansong.demo.dtv5core.dto;

import java.util.Map;

public class TongdInfoDto {
	
	private String tongdChanCode;
	private int tongdCount;
	private Map<Integer, String> tongdConn;
	
	public String getTongdChanCode() {
		return tongdChanCode;
	}
	public void setTongdChanCode(String tongdChanCode) {
		this.tongdChanCode = tongdChanCode;
	}
	public int getTongdCount() {
		return tongdCount;
	}
	public void setTongdCount(int tongdCount) {
		this.tongdCount = tongdCount;
	}
	public Map<Integer, String> getTongdConn() {
		return tongdConn;
	}
	public void setTongdConn(Map<Integer, String> tongdConn) {
		this.tongdConn = tongdConn;
	}

}
