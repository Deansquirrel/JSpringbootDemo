package com.yuansong.demo.boot.jwt.utils;

import io.jsonwebtoken.Claims;

public class CheckResult {
	
	private Boolean success;
	private Claims claims;
	private JWT_ERRCODE errCode;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Claims getClaims() {
		return claims;
	}
	public void setClaims(Claims claims) {
		this.claims = claims;
	}
	public JWT_ERRCODE getErrCode() {
		return errCode;
	}
	public void setErrCode(JWT_ERRCODE errCode) {
		this.errCode = errCode;
	}

}
