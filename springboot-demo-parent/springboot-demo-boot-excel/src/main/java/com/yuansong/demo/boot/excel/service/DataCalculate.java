package com.yuansong.demo.boot.excel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.yuansong.demo.boot.excel.DO.CountResource;
import com.yuansong.demo.boot.excel.DO.CountResult;
import com.yuansong.demo.boot.excel.DTO.BaseData;

@Component
public class DataCalculate implements  CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(DataCalculate.class);
	
	private final String filePath = "data";
	private final String fileName = "data.xlsx";
	
	private final String csSheetName = "传输";
	private final String dhSheetName = "动环";
	private final String jkSheetName = "家客";
	
	private final String titlePrefix = "级告警_《";
	private final String titleSuffix = "》";
	
	private final String outputSheetSuffix = "每日工单分析";
	
	@Autowired
	private Message msg;
	
	@Autowired
	private DataReader dataReader;
	
	@Autowired
	private DataWriter dataWriter;

	@Override
	public void run(String... args) throws Exception {
		long beginTime = System.currentTimeMillis();
		XSSFWorkbook wb = null;
		try {
			wb = this.dataReader.getXSSFWorkbook(this.getFilePath());
			if(wb == null) {
				String errMsg ="未读取到Excel文件"; 
				throw new Exception(errMsg);
			}
			this.subRun(wb, csSheetName, 0, 4);
			this.subRun(wb, dhSheetName, 0, 4);
			this.subRun(wb, jkSheetName, 0, 4);
			this.saveFile(wb);
		} catch (Exception e) {
			this.msg.print(e.getMessage());
		} finally {
			if(wb != null) {
				try {
					wb.close();					
				} catch (IOException e) {
					//忽略文件关闭异常
				}
			}
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("\n" +
                "运行耗时: " + (endTime - beginTime) + "毫秒 \n");
		
		try {
			System.in.read();			
		} catch (Exception e) {
			return;
		}	
	}
	
	private void subRun(XSSFWorkbook wb, String sheetName, int colIndex1, int colIndex2) throws Exception {
		long begTime = -1L;
		long endTime = -1L;

		List<BaseData> baseDataList = null;
		try {
			this.msg.print("读取" + sheetName + "数据 开始");
			begTime = System.currentTimeMillis();
			baseDataList = this.dataReader.getData(wb, sheetName, colIndex1, colIndex2);
			endTime = System.currentTimeMillis();
			this.msg.print("读取" + sheetName + "数据 完成"
					+ " 数据量 " + String.valueOf(baseDataList.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception("读取" +sheetName + "数据时发生异常，" + e.getMessage());
		}
		List<CountResource> resourceDataList = null;
		try {
			this.msg.print("计算" + sheetName + "数据 开始");
			begTime = System.currentTimeMillis();
			resourceDataList = this.getResource(baseDataList, wb, sheetName);
			endTime = System.currentTimeMillis();
			this.msg.print("计算" + sheetName + "数据 完成"
					+ " " + "数据量 " + String.valueOf(resourceDataList.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception("计算" + sheetName + "数据时发生异常，" + e.getMessage());
		}
		List<CountResult> resultDataList = null;
		try {
			this.msg.print("汇总" + sheetName + "数据 开始");
			begTime = System.currentTimeMillis();
			resultDataList = this.getResult(resourceDataList);
			endTime = System.currentTimeMillis();
			this.msg.print("汇总" + sheetName + "数据 完成"
					+ " " + "数据量 " + String.valueOf(resultDataList.size()) + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception("汇总" + sheetName + "数据时发生异常，" + e.getMessage());
		}
		try {
			this.msg.print("输出" + sheetName + "数据 开始");
			begTime = System.currentTimeMillis();
			this.dataWriter.writeSheet(wb, sheetName + this.outputSheetSuffix, resultDataList);
			endTime = System.currentTimeMillis();
			this.msg.print("输出" + sheetName + "数据 完成");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception("输出" + sheetName + "数据时发生异常，" + e.getMessage());
		}
	}
	
	private void saveFile(XSSFWorkbook wb) throws Exception {
		long begTime = -1L;
		long endTime = -1L;
		try {
			this.msg.print("保存 开始");
			begTime = System.currentTimeMillis();
			this.dataWriter.saveWorkbook(this.getFilePath(), wb);
			endTime = System.currentTimeMillis();
			this.msg.print("保存 完成"  + " 耗时 " + String.valueOf(endTime - begTime) + " 毫秒");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception("保存时发生异常，" + e.getMessage());
		}
	}
		
	private List<CountResource> getResource(List<BaseData> dList, XSSFWorkbook wb, String sheetName) {
		List<CountResource> list = new ArrayList<CountResource>();
		CountResource cr = null;
		for(BaseData d : dList) {
			String title = this.getTitle(d.getRwm());
			if(title == null || "".equals(title)) {
				this.msg.print("==========================");
				this.msg.print("异常数据【任务标题获取失败】：" + sheetName + " " + String.valueOf(d.getRowIndex()));
				this.msg.print("==========================");
				this.dataWriter.writeErrRowStyle(wb, sheetName, d.getRowIndex());
			} else {
				cr = new CountResource();
				cr.setTitle(title);
				cr.setDs(d.getDs());
				list.add(cr);
			}
		}
		return list;
	}
	
	private List<CountResult> getResult(List<CountResource> dList) {
		Map<String, CountResult> dMap = new HashMap<String, CountResult>();
		CountResult r = null;
		for(CountResource cr : dList) {
			if(dMap.containsKey(cr.getTitle())) {
				r = dMap.get(cr.getTitle());
				Map<String, Integer> detail = r.getDetail();
				if(detail.containsKey(cr.getDs())) {
					detail.put(cr.getDs(), detail.get(cr.getDs()) + 1);
				} else {
					detail.put(cr.getDs(), 1);
				}
				r.setDetail(detail);
				r.setTotal(r.getTotal() + 1);
			} else {
				r = new CountResult();
				Map<String, Integer> detail = new HashMap<String, Integer>();
				detail.put(cr.getDs(), 1);
				r.setHbq(cr.getTitle());;
				r.setDetail(detail);
				r.setTotal(1);
			}
			dMap.put(cr.getTitle(), r);
		}
		List<CountResult> list = new ArrayList<CountResult>();
		for(CountResult cr : dMap.values()) {
			list.add(cr);
		}
		return list;
	}
	
	private String getTitle(String rwm) {
		if(rwm == null) {
			return null;
		}
		int p = rwm.indexOf(this.titlePrefix);
		if(p < 0) {
			return null;
		}
		int s = rwm.substring(p + this.titlePrefix.length()).indexOf(this.titleSuffix);
		if(s < 0) {
			return null;
		}
		return rwm.substring(p + this.titlePrefix.length(), p + this.titlePrefix.length() + s);
	}
	
	private String getFilePath() throws Exception {
		return this.getDataPath() +this.fileName;
	}
	
	private String getDataPath() throws Exception {
		return ResourceUtils.getURL(this.filePath).getPath();
	}
}
