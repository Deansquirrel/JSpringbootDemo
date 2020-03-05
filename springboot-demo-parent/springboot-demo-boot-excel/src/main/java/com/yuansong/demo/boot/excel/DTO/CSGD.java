package com.yuansong.demo.boot.excel.DTO;

import java.util.Date;

public class CSGD {
	//任务名
	private String rwm;
	//工单编号
	private String gdbh;
	//告警流水号
	private String gjlsh;
	//告警来源
	private String gjly;
	//故障发生地市
	private String gzfsds;
	//发起人
	private String fqr;
	//发起时间
	private Date fqsj;
	//回复时间
	private Date hfsj;
	//工单状态
	private String gdzt;
	//故障发生时间
	private Date gzfssj;
	//告警清除时间
	private Date gjqcsj;
	//网元设备厂家
	private String wysbcj;
	//网络一级分类
	private String wlyjfl;
	//网络二级分类
	private String wlejfl;
	//网络三级分类
	private String wlsjfl;
	//故障原因分类（一级）
	private String gzyyflyj;
	//故障原因分类（二级）
	private String gzyyflej;
	//T1处理人
	private String t1clr;
	//T1处理人部门
	private String t1clrbm;
	//处理是否超时
	private Boolean clsfcst1;
	//T1处理组(规则)
	private String t1clzgz;
	//T2处理人
	private String t2clr;
	//T2处理人部门
	private String t2clrbm;
	//处理是否超时
	private Boolean clsfcst2;
	//T2处理组(规则)
	private String t2clzgz;
	//T3处理人
	private String t3clr;
	//T3处理人部门
	private String t3clrbm;
	//处理是否超时
	private Boolean clsfcst3;
	//T3处理组(规则)
	private String t3clzgz;
	//归档人
	private String gdr;
	//工单归档时间
	private Date gdgdsj;
	//处理措施
	private String clcs;
	//派单类型
	private String pdlx;
	//归档类型
	private String gdlx;
	//告警名称
	private String gjmc;
	//告警发现时间
	private Date gjfxsj;
	//是否驳回T1环节
	private Boolean sfbht1hj;
	//T2是否移交
	private Boolean t2sfyj;
	//T2是否驳回
	private Boolean t2sfbh;
	//子告警数量
	private Integer zgjsl;
	//采取措施时间
	private Date cqcssj;
	//T2受理人
	private String t2slr;
	//T2受理部门
	private String t2slbm;
	//T2受理时间
	private Date t2slsj;
	//到达T2环节时间
	private Date ddt2hjsj;
	//预处理状态
	private String yclzt;
	//硬件名称
	private String yjmc;
	//网元名称
	private String wymc;
	//是否铁塔告警
	private String sfttgj;
	public String getRwm() {
		return rwm;
	}
	public void setRwm(String rwm) {
		this.rwm = rwm;
	}
	public String getGdbh() {
		return gdbh;
	}
	public void setGdbh(String gdbh) {
		this.gdbh = gdbh;
	}
	public String getGjlsh() {
		return gjlsh;
	}
	public void setGjlsh(String gjlsh) {
		this.gjlsh = gjlsh;
	}
	public String getGjly() {
		return gjly;
	}
	public void setGjly(String gjly) {
		this.gjly = gjly;
	}
	public String getGzfsds() {
		return gzfsds;
	}
	public void setGzfsds(String gzfsds) {
		this.gzfsds = gzfsds;
	}
	public String getFqr() {
		return fqr;
	}
	public void setFqr(String fqr) {
		this.fqr = fqr;
	}
	public Date getFqsj() {
		return fqsj;
	}
	public void setFqsj(Date fqsj) {
		this.fqsj = fqsj;
	}
	public Date getHfsj() {
		return hfsj;
	}
	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}
	public String getGdzt() {
		return gdzt;
	}
	public void setGdzt(String gdzt) {
		this.gdzt = gdzt;
	}
	public Date getGzfssj() {
		return gzfssj;
	}
	public void setGzfssj(Date gzfssj) {
		this.gzfssj = gzfssj;
	}
	public Date getGjqcsj() {
		return gjqcsj;
	}
	public void setGjqcsj(Date gjqcsj) {
		this.gjqcsj = gjqcsj;
	}
	public String getWysbcj() {
		return wysbcj;
	}
	public void setWysbcj(String wysbcj) {
		this.wysbcj = wysbcj;
	}
	public String getWlyjfl() {
		return wlyjfl;
	}
	public void setWlyjfl(String wlyjfl) {
		this.wlyjfl = wlyjfl;
	}
	public String getWlejfl() {
		return wlejfl;
	}
	public void setWlejfl(String wlejfl) {
		this.wlejfl = wlejfl;
	}
	public String getWlsjfl() {
		return wlsjfl;
	}
	public void setWlsjfl(String wlsjfl) {
		this.wlsjfl = wlsjfl;
	}
	public String getGzyyflyj() {
		return gzyyflyj;
	}
	public void setGzyyflyj(String gzyyflyj) {
		this.gzyyflyj = gzyyflyj;
	}
	public String getGzyyflej() {
		return gzyyflej;
	}
	public void setGzyyflej(String gzyyflej) {
		this.gzyyflej = gzyyflej;
	}
	public String getT1clr() {
		return t1clr;
	}
	public void setT1clr(String t1clr) {
		this.t1clr = t1clr;
	}
	public String getT1clrbm() {
		return t1clrbm;
	}
	public void setT1clrbm(String t1clrbm) {
		this.t1clrbm = t1clrbm;
	}
	public Boolean getClsfcst1() {
		return clsfcst1;
	}
	public void setClsfcst1(Boolean clsfcst1) {
		this.clsfcst1 = clsfcst1;
	}
	public String getT1clzgz() {
		return t1clzgz;
	}
	public void setT1clzgz(String t1clzgz) {
		this.t1clzgz = t1clzgz;
	}
	public String getT2clr() {
		return t2clr;
	}
	public void setT2clr(String t2clr) {
		this.t2clr = t2clr;
	}
	public String getT2clrbm() {
		return t2clrbm;
	}
	public void setT2clrbm(String t2clrbm) {
		this.t2clrbm = t2clrbm;
	}
	public Boolean getClsfcst2() {
		return clsfcst2;
	}
	public void setClsfcst2(Boolean clsfcst2) {
		this.clsfcst2 = clsfcst2;
	}
	public String getT2clzgz() {
		return t2clzgz;
	}
	public void setT2clzgz(String t2clzgz) {
		this.t2clzgz = t2clzgz;
	}
	public String getT3clr() {
		return t3clr;
	}
	public void setT3clr(String t3clr) {
		this.t3clr = t3clr;
	}
	public String getT3clrbm() {
		return t3clrbm;
	}
	public void setT3clrbm(String t3clrbm) {
		this.t3clrbm = t3clrbm;
	}
	public Boolean getClsfcst3() {
		return clsfcst3;
	}
	public void setClsfcst3(Boolean clsfcst3) {
		this.clsfcst3 = clsfcst3;
	}
	public String getT3clzgz() {
		return t3clzgz;
	}
	public void setT3clzgz(String t3clzgz) {
		this.t3clzgz = t3clzgz;
	}
	public String getGdr() {
		return gdr;
	}
	public void setGdr(String gdr) {
		this.gdr = gdr;
	}
	public Date getGdgdsj() {
		return gdgdsj;
	}
	public void setGdgdsj(Date gdgdsj) {
		this.gdgdsj = gdgdsj;
	}
	public String getClcs() {
		return clcs;
	}
	public void setClcs(String clcs) {
		this.clcs = clcs;
	}
	public String getPdlx() {
		return pdlx;
	}
	public void setPdlx(String pdlx) {
		this.pdlx = pdlx;
	}
	public String getGdlx() {
		return gdlx;
	}
	public void setGdlx(String gdlx) {
		this.gdlx = gdlx;
	}
	public String getGjmc() {
		return gjmc;
	}
	public void setGjmc(String gjmc) {
		this.gjmc = gjmc;
	}
	public Boolean getSfbht1hj() {
		return sfbht1hj;
	}
	public void setSfbht1hj(Boolean sfbht1hj) {
		this.sfbht1hj = sfbht1hj;
	}
	public Boolean getT2sfyj() {
		return t2sfyj;
	}
	public void setT2sfyj(Boolean t2sfyj) {
		this.t2sfyj = t2sfyj;
	}
	public Boolean getT2sfbh() {
		return t2sfbh;
	}
	public void setT2sfbh(Boolean t2sfbh) {
		this.t2sfbh = t2sfbh;
	}
	public Integer getZgjsl() {
		return zgjsl;
	}
	public void setZgjsl(Integer zgjsl) {
		this.zgjsl = zgjsl;
	}
	public Date getCqcssj() {
		return cqcssj;
	}
	public void setCqcssj(Date cqcssj) {
		this.cqcssj = cqcssj;
	}
	public String getT2slr() {
		return t2slr;
	}
	public void setT2slr(String t2slr) {
		this.t2slr = t2slr;
	}
	public String getT2slbm() {
		return t2slbm;
	}
	public void setT2slbm(String t2slbm) {
		this.t2slbm = t2slbm;
	}
	public Date getT2slsj() {
		return t2slsj;
	}
	public void setT2slsj(Date t2slsj) {
		this.t2slsj = t2slsj;
	}
	public Date getDdt2hjsj() {
		return ddt2hjsj;
	}
	public void setDdt2hjsj(Date ddt2hjsj) {
		this.ddt2hjsj = ddt2hjsj;
	}
	public String getYclzt() {
		return yclzt;
	}
	public void setYclzt(String yclzt) {
		this.yclzt = yclzt;
	}
	public String getYjmc() {
		return yjmc;
	}
	public void setYjmc(String yjmc) {
		this.yjmc = yjmc;
	}
	public String getWymc() {
		return wymc;
	}
	public void setWymc(String wymc) {
		this.wymc = wymc;
	}
	public String getSfttgj() {
		return sfttgj;
	}
	public void setSfttgj(String sfttgj) {
		this.sfttgj = sfttgj;
	}
	public Date getGjfxsj() {
		return gjfxsj;
	}
	public void setGjfxsj(Date gjfxsj) {
		this.gjfxsj = gjfxsj;
	}

}
