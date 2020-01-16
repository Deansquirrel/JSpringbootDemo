package com.yuansong.demo.boot.dtv5coretest.controller.req;

public class SendOfferReq {
	
	private String pmChan;
	private String pmMubXitLeix;
	private Long pmMubXitID;
	private Integer atom;
	
	public String getPmChan() {
		return pmChan;
	}
	public void setPmChan(String pmChan) {
		this.pmChan = pmChan;
	}
	public String getPmMubXitLeix() {
		return pmMubXitLeix;
	}
	public void setPmMubXitLeix(String pmMubXitLeix) {
		this.pmMubXitLeix = pmMubXitLeix;
	}
	public Long getPmMubXitID() {
		return pmMubXitID;
	}
	public void setPmMubXitID(Long pmMubXitID) {
		this.pmMubXitID = pmMubXitID;
	}
	public Integer getAtom() {
		return atom;
	}
	public void setAtom(Integer atom) {
		this.atom = atom;
	}
	
}
