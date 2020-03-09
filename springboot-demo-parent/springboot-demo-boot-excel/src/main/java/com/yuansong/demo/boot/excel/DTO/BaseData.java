package com.yuansong.demo.boot.excel.DTO;

public class BaseData {
	private Integer rowIndex;
	private String rwm;
	private String ds;
	public String getDs() {
		return ds;
	}
	public void setDs(String ds) {
		this.ds = ds;
	}
	public String getRwm() {
		return rwm;
	}
	public void setRwm(String rwm) {
		this.rwm = rwm;
	}
	public Integer getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}
}
