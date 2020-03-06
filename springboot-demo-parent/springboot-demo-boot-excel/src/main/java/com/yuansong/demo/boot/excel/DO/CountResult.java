package com.yuansong.demo.boot.excel.DO;

import java.util.Map;

public class CountResult implements Comparable<CountResult> {
	
	//行标签
	private String hbq;
	private Map<String, Integer> detail;
	private Integer total;
	public String getHbq() {
		return hbq;
	}
	public void setHbq(String hbq) {
		this.hbq = hbq;
	}
	public Map<String, Integer> getDetail() {
		return detail;
	}
	public void setDetail(Map<String, Integer> detail) {
		this.detail = detail;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	@Override
	public int compareTo(CountResult o) {
		if(this.getTotal() - o.getTotal() == 0) {
			return this.getHbq().compareTo(o.getHbq());
		} else {
			return o.getTotal().compareTo(this.getTotal());
		}
	}
	
	

}
