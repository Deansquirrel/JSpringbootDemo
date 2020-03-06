package com.yuansong.demo.boot.excel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
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
		CountResult cr = null;
		
		Collections.sort(list);
		
		int dIndex = -1;
		for(int i=0;i < list.size(); i++) {
			cr = list.get(i);
			row = sheet.createRow(i + 1);
			for(int j = 0; j <= dsList.size(); j++) {
				row.createCell(j).setCellStyle(cellStyle);	
			}
			row.getCell(0).setCellValue(cr.getHbq());
			for(String ds : cr.getDetail().keySet()) {
				if(dsList.indexOf(ds) > -1) {
					dIndex = dsList.indexOf(ds) + 1;
					row.getCell(dIndex).setCellValue(cr.getDetail().get(ds));
				} else {
					this.msg.print(cr.getHbq() + " " + ds + " " + String.valueOf(cr.getDetail().get(ds)));
				}
			}
			row.getCell(dsList.size()).setCellValue(cr.getTotal());
		}
		
		try {
			this.setAutoSizeColumn(sheet, 0);
		} catch(Exception e) {
			
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
	
	private void setAutoSizeColumn(XSSFSheet sheet, int col) throws UnsupportedEncodingException {
		sheet.autoSizeColumn(col);//只支持英文和数字
		int columnWidth = sheet.getColumnWidth(col) / 256;
		int intlength = -1;
		for (int row = 0; row <= sheet.getLastRowNum(); ++row) {
			Cell cell = sheet.getRow(row).getCell(col);//如果有合并单元格，就会有getCell()==null的情况，需要createCell();
			if (cell == null) {
				cell = sheet.getRow(row).createCell(col);
			}
	        intlength = cell.toString().getBytes("GBK").length;  
	        if (columnWidth < intlength + 1) {  
	        	columnWidth = intlength + 1;  
	    	}
		}
		sheet.setColumnWidth(col, columnWidth * 256); //对中文列调整列宽
	}

}
