package com.yuansong.demo.boot.excel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.yuansong.demo.boot.excel.DO.CountResult;

@Component
public class DataWriter {
	
	//常州	淮安	连云港	南京	南通	苏州	泰州	无锡	宿迁	徐州	盐城	扬州	镇江
	
	@Autowired
	private Message msg;
	
	public String saveData(
			List<CountResult> cs, 
			List<CountResult> dh, 
			List<CountResult> jk) throws Exception {
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet = null;
		
		sheet = workbook.createSheet("传输每日工单分析");
		this.sheetInit(sheet, this.getHSSFCellStyle(workbook));
		this.saveSheetData(sheet, cs, this.getHSSFCellStyle(workbook));
		
		sheet = workbook.createSheet("动环每日工单分析");
		this.sheetInit(sheet, this.getHSSFCellStyle(workbook));
		this.saveSheetData(sheet, dh, this.getHSSFCellStyle(workbook));
		
		sheet = workbook.createSheet("家客每日工单分析");
		this.sheetInit(sheet, this.getHSSFCellStyle(workbook));
		this.saveSheetData(sheet, jk, this.getHSSFCellStyle(workbook));
		
		this.checkAndCreateFolder(this.getOutputPath());
        
        //文档输出
        FileOutputStream out = null;
        String fileName = String.valueOf(System.currentTimeMillis()) + ".xlsx";
        try {
        	out = new FileOutputStream(this.getOutputPath() + File.separator + fileName);
        	workbook.write(out);
        } finally {
        	try {
        		out.close();
        	} catch(Exception e ) {
        		e.printStackTrace();
        	}
        }
        return fileName;
	}
	
	private void saveSheetData(XSSFSheet sheet, List<CountResult> list, CellStyle cellStyle) {
		List<String> hbqList = new ArrayList<String>();
		for(CountResult r : list) {
			if(!hbqList.contains(r.getHbq())) {
				hbqList.add(r.getHbq());
			}
		}
		XSSFRow row = null;
		List<String> dsList = this.getDsList();
		Map<String, Integer> hbqRel = new HashMap<String, Integer>();
		for(int i = 0; i < hbqList.size(); i++) {
			row = sheet.createRow(i + 1);
			row.createCell(0).setCellStyle(cellStyle);
			row.getCell(0).setCellValue(hbqList.get(i));
			hbqRel.put(hbqList.get(i), i + 1);
			for(int j = 1; j < dsList.size() + 1; j++) {
				row.createCell(j).setCellStyle(cellStyle);
			}
		}
		for(CountResult r : list) {
			row = sheet.getRow(hbqRel.get(r.getHbq()));
			if(dsList.indexOf(r.getDs()) >= 0) {
				row.getCell(dsList.indexOf(r.getDs()) + 1).setCellValue(String.valueOf(r.getTimes()));				
			} else {
				this.msg.print(r.getHbq() + " " + r.getDs() + " " + String.valueOf(r.getTimes()));
			}
		}
		//总计数据
		Map<String, Integer> total = new HashMap<String, Integer>();
		for(CountResult r : list) {
			if(total.containsKey(r.getHbq())) {
				total.put(r.getHbq(), total.get(r.getHbq()) + r.getTimes());
			} else {
				total.put(r.getHbq(), r.getTimes());
			}
		}
		for(String k : total.keySet()) {
			row = sheet.getRow(hbqRel.get(k));
			row.getCell(dsList.size()).setCellValue(String.valueOf(total.get(k)));
		}
		
		
	}
	
	private void sheetInit(XSSFSheet sheet, CellStyle cellStyle) {
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("行标签");
		row.getCell(0).setCellStyle(cellStyle);
		int col = 1;
		for(String ds : this.getDsList()) {
			row.createCell(col).setCellValue(ds);
			row.getCell(col).setCellStyle(cellStyle);
			col++;
		}
	}
	
	private List<String> getDsList() {
		List<String> str = new ArrayList<String>();
		str.add("常州");
		str.add("淮安");
		str.add("连云港");
		str.add("南京");
		str.add("南通");
		str.add("苏州");
		str.add("泰州");
		str.add("无锡");
		str.add("宿迁");
		str.add("徐州");
		str.add("盐城");
		str.add("扬州");
		str.add("镇江");
		str.add("总计");
		return str;
	}
	
	private XSSFCellStyle getHSSFCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();

		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);

		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		
		return cellStyle;
	}
	
	private void checkAndCreateFolder(String path) {
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	public String getOutputPath() throws Exception {
		return ResourceUtils.getURL("output").getPath();
	}

}
