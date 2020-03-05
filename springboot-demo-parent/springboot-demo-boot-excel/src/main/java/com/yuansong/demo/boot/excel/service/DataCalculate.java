package com.yuansong.demo.boot.excel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuansong.demo.boot.excel.DO.CountResource;
import com.yuansong.demo.boot.excel.DO.CountResult;
import com.yuansong.demo.boot.excel.DTO.CSGD;
import com.yuansong.demo.boot.excel.DTO.CSGJ;
import com.yuansong.demo.boot.excel.DTO.DHGD;
import com.yuansong.demo.boot.excel.DTO.DHGJ;
import com.yuansong.demo.boot.excel.DTO.JKGD;
import com.yuansong.demo.boot.excel.DTO.JKGJ;

@Component
public class DataCalculate {
	
	private static final Logger logger = LoggerFactory.getLogger(DataCalculate.class);
	
	@Autowired
	private Message msg;
	
	@Autowired
	private DataReader dataReader;
	
	@Autowired
	private DataWriter dataWriter;
	
	public void subRun() {
		long begTime = -1L;
		long endTime = -1L;
		List<CSGJ> listCSGJ = null;
		try {
			this.msg.print("读取传输告警数据 开始");
			begTime = System.currentTimeMillis();
			listCSGJ = this.dataReader.getCSGJData();
			endTime = System.currentTimeMillis();
			this.msg.print("读取传输告警数据 完成");
			this.msg.print("数据量 " + String.valueOf(listCSGJ.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			this.msg.print("读取传输告警数据时发生异常，" + e.getMessage());
			return;
		}
		
		List<CSGD> listCSGD = null;
		try {
			this.msg.print("读取传输工单数据 开始");
			begTime = System.currentTimeMillis();
			listCSGD = this.dataReader.getCSGDData();
			endTime = System.currentTimeMillis();
			this.msg.print("读取传输工单数据 完成");
			this.msg.print("数据量 " + String.valueOf(listCSGD.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			this.msg.print("读取传输工单数据时发生异常，" + e.getMessage());
			return;
		}
		
		List<DHGJ> listDHGJ = null;
		try {
			this.msg.print("读取动环告警数据 开始");
			begTime = System.currentTimeMillis();
			listDHGJ = this.dataReader.getDHGJData();
			endTime = System.currentTimeMillis();
			this.msg.print("读取动环告警数据 完成");
			this.msg.print("数据量 " + String.valueOf(listDHGJ.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			this.msg.print("读取动环告警数据时发生异常，" + e.getMessage());
			return;
		}
		
		List<DHGD> listDHGD = null;
		try {
			this.msg.print("读取动环工单数据 开始");
			begTime = System.currentTimeMillis();
			listDHGD = this.dataReader.getDHGDData();
			endTime = System.currentTimeMillis();
			this.msg.print("读取动环工单数据 完成");
			this.msg.print("数据量 " + String.valueOf(listDHGD.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			this.msg.print("读取动环工单数据时发生异常，" + e.getMessage());
			return;
		}
		
		List<JKGJ> listJKGJ = null;
		try {
			this.msg.print("读取家客告警数据 开始");
			begTime = System.currentTimeMillis();
			listJKGJ = this.dataReader.getJKGJData();
			endTime = System.currentTimeMillis();
			this.msg.print("读取家客告警数据 完成");
			this.msg.print("数据量 " + String.valueOf(listJKGJ.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			this.msg.print("读取家客告警数据时发生异常，" + e.getMessage());
			return;
		}
		
		List<JKGD> listJKGD = null;
		try {
			this.msg.print("读取家客工单数据 开始");
			begTime = System.currentTimeMillis();
			listJKGD = this.dataReader.getJKGDData();
			endTime = System.currentTimeMillis();
			this.msg.print("读取家客工单数据 完成");
			this.msg.print("数据量 " + String.valueOf(listJKGD.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			this.msg.print("读取家客工单数据时发生异常，" + e.getMessage());
			return;
		}
		
		this.msg.print("计算传输数据 开始");
		List<CountResource> csResult = null;
		try {
			begTime = System.currentTimeMillis();
			csResult = this.getCSResourceList(listCSGD, listCSGJ);
			endTime = System.currentTimeMillis();
			this.msg.print("计算传输数据 完成 " + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			this.msg.print("计算传输数据时发生异常，" + e.getMessage());
			return;
		}
		
		this.msg.print("计算动环数据 开始");
		List<CountResource> dhResult = null;
		try {
			begTime = System.currentTimeMillis();
			dhResult = this.getDHResourceList(listDHGD, listDHGJ);
			endTime = System.currentTimeMillis();
			this.msg.print("计算动环数据 完成 " + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			this.msg.print("计算动环数据时发生异常，" + e.getMessage());
			return;
		}
		
		this.msg.print("计算家客数据 开始");
		List<CountResource> jkResult = null;
		try {
			begTime = System.currentTimeMillis();
			jkResult = this.getJKResourceList(listJKGD, listJKGJ);
			endTime = System.currentTimeMillis();
			this.msg.print("计算家客数据 完成 " + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			this.msg.print("计算家客数据时发生异常，" + e.getMessage());
			return;
		}
		
		this.msg.print("保存数据 开始");
		try {
			begTime = System.currentTimeMillis();
			String path = this.dataWriter.saveData(
					this.getResultList(csResult),
					this.getResultList(dhResult),
					this.getResultList(jkResult));
			endTime = System.currentTimeMillis();
			this.msg.print("保存数据 完成 " + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
			this.msg.print("输出文件：" + path);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			this.msg.print("保存数据时发生异常，" + e.getMessage());
			return;
		}
	}

	private List<CountResource> getCSResourceList(List<CSGD> gdList, List<CSGJ> gjList) throws Exception {
		
		Map<String, CSGD> gdMap = new HashMap<String, CSGD>();
		for(CSGD gd : gdList) {
			String key = gd.getGdbh();
			if(key == null || "".equals(key.trim())) {
				this.msg.print("WARN 传输工单 工单编号为空");
			} else {
				gdMap.put(key.trim(), gd);
			}
		}
		Map<String, CSGJ> gjMap = new HashMap<String, CSGJ>();
		for(CSGJ gj : gjList) {
			String key = gj.getGdh();
			if(key == null || "".equals(key.trim())) {
				this.msg.print("WARN 传输告警 工单号为空");
			} else {
				gjMap.put(key.trim(), gj);
			}
		}
		
		List<CountResource> list = new ArrayList<CountResource>();
		for(String gdh : gdMap.keySet()) {
			CountResource r = new CountResource();
			r.setGdbh(gdh);
			r.setDs(gdMap.get(gdh).getGzfsds());
			if(gjMap.containsKey(gdh)) {
				String gjbt = gjMap.get(gdh).getGjbt();
				if(gjbt != null && !"".equals(gjbt.trim())) {
					r.setGjbt(gjbt);
				}
			}
			if(r.getGjbt() == null) {
				r.setGjbt(gdMap.get(gdh).getRwm());
			}
			list.add(r);
		}
		return list;
	}
	
	private List<CountResource> getDHResourceList(List<DHGD> gdList, List<DHGJ> gjList) {
		Map<String, DHGD> gdMap = new HashMap<String, DHGD>();
		for(DHGD gd : gdList) {
			String key = gd.getGdbh();
			if(key == null || "".equals(key.trim())) {
				this.msg.print("WARN 动环工单 工单编号为空");
			} else {
				gdMap.put(key.trim(), gd);
			}
		}
		Map<String, DHGJ> gjMap = new HashMap<String, DHGJ>();
		for(DHGJ gj : gjList) {
			String key = gj.getGdh();
			if(key == null || "".equals(key.trim())) {
				this.msg.print("WARN 动环告警 工单号为空");
			} else {
				gjMap.put(key.trim(), gj);
			}
		}
		
		List<CountResource> list = new ArrayList<CountResource>();
		for(String gdh : gdMap.keySet()) {
			CountResource r = new CountResource();
			r.setGdbh(gdh);
			r.setDs(gdMap.get(gdh).getGzfsds());
			if(gjMap.containsKey(gdh)) {
				String gjbt = gjMap.get(gdh).getGjbt();
				if(gjbt != null && !"".equals(gjbt.trim())) {
					r.setGjbt(gjbt);
				}
			}
			if(r.getGjbt() == null) {
				r.setGjbt(gdMap.get(gdh).getRwm());
			}
			list.add(r);
		}
		return list;
	}
	
	private List<CountResource> getJKResourceList(List<JKGD> gdList, List<JKGJ> gjList) {
		Map<String, JKGD> gdMap = new HashMap<String, JKGD>();
		for(JKGD gd : gdList) {
			String key = gd.getGdbh();
			if(key == null || "".equals(key.trim())) {
				this.msg.print("WARN 动环工单 工单编号为空");
			} else {
				gdMap.put(key.trim(), gd);
			}
		}
		Map<String, JKGJ> gjMap = new HashMap<String, JKGJ>();
		for(JKGJ gj : gjList) {
			String key = gj.getGdh();
			if(key == null || "".equals(key.trim())) {
				this.msg.print("WARN 动环告警 工单号为空");
			} else {
				gjMap.put(key.trim(), gj);
			}
		}
		
		List<CountResource> list = new ArrayList<CountResource>();
		for(String gdh : gdMap.keySet()) {
			CountResource r = new CountResource();
			r.setGdbh(gdh);
			r.setDs(gdMap.get(gdh).getGzfsds());
			if(gjMap.containsKey(gdh)) {
				String gjbt = gjMap.get(gdh).getGjbt();
				if(gjbt != null && !"".equals(gjbt.trim())) {
					r.setGjbt(gjbt);
				}
			}
			if(r.getGjbt() == null) {
				r.setGjbt(gdMap.get(gdh).getRwm());
			}
			list.add(r);
		}
		return list;
	}
	
	private List<CountResult> getResultList(List<CountResource> list) {
		Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> dsList = null;
		for(CountResource res : list) {
			dsList = null;
			if(data.containsKey(res.getGjbt())) {
				//告警标题已存在
				dsList = data.get(res.getGjbt());
				if(dsList.containsKey(res.getDs())) {
					//地市已存在
					dsList.put(res.getDs(), dsList.get(res.getDs()) + 1);
				} else {
					//地市不存在
					dsList.put(res.getDs(), 1);
				}
				data.put(res.getGjbt(), dsList);
			} else {
				//告警标题不存在
				dsList = new HashMap<String, Integer>();
				dsList.put(res.getDs(), 1);
				data.put(res.getGjbt(), dsList);
			}
		}
		
		List<CountResult> result = new ArrayList<CountResult>();
		CountResult r = null;
		for(String gjbt : data.keySet()) {
			for (String ds : data.get(gjbt).keySet()) {
				 r = new CountResult();
				 r.setHbq(gjbt);
				 r.setDs(ds);
				 r.setTimes(data.get(gjbt).get(ds));
				 result.add(r);
			}
		}
		return result;
	}
}
