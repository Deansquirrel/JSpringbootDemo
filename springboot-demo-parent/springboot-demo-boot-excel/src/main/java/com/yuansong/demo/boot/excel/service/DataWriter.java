package com.yuansong.demo.boot.excel.service;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuansong.demo.boot.excel.DO.CountResult;

@Component
public class DataWriter {
	
	//常州	淮安	连云港	南京	南通	苏州	泰州	无锡	宿迁	徐州	盐城	扬州	镇江
	
	@Autowired
	private Message msg;
	
	public void writeSheet(XSSFWorkbook workbook, String sheetName, List<CountResult> data) throws Exception {
		if(workbook.getSheetIndex(sheetName) > -1) {
			workbook.removeSheetAt(workbook.getSheetIndex(sheetName));
		}
		XSSFSheet sheet = null;
		sheet = workbook.createSheet(sheetName);
		this.sheetInit(sheet, this.getHSSFCellStyle(workbook));
		this.dataSaveClean(data);
		this.saveSheetData(sheet, data, this.getHSSFCellStyle(workbook));
	}
	
	public void saveWorkbook(String filePath, XSSFWorkbook workbook) throws Exception {
        FileOutputStream out = null;
        try {
        	out = new FileOutputStream(filePath);
        	workbook.write(out);
        } catch(Exception e) {
        	throw e;
        }
        finally {
        	try {
        		out.close();
        	} catch(Exception e ) {
        		e.printStackTrace();
        	}
        }
	}
	
	private void dataSaveClean(List<CountResult> list) {
		List<String> dsList = this.getDsList();
		Map<String, Integer> detail = null;
		List<String> tList = new ArrayList<String>();
		for(CountResult cr : list) {
			 detail = cr.getDetail();
			 tList.clear();
			 for(String ds : detail.keySet()) {
				 if(dsList.indexOf(ds) < 0) {
					 cr.setTotal(cr.getTotal() - detail.get(ds));
					 tList.add(ds);
				 }
			 }
			 for(String ds : tList) {
				 this.msg.print("==========================");
					this.msg.print("异常数据【地市不在规定范围】：" + cr.getHbq() + " " + ds + " " + String.valueOf(cr.getDetail().get(ds)));
					this.msg.print("==========================");
				 detail.remove(ds);
			 }
			 cr.setDetail(detail);
		}
		Iterator<CountResult> iterator = list.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getTotal() == 0) {
            	iterator.remove();
            }
        }
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
//					数据清洗过程已包含
//					this.msg.print("==========================");
//					this.msg.print("异常数据【地市不在规定范围】：" + cr.getHbq() + " " + ds + " " + String.valueOf(cr.getDetail().get(ds)));
//					this.msg.print("==========================");
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
	
	public void writeErrRowStyle(XSSFWorkbook wb, String sheetName, int rowIndex) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		if(sheet == null) {
			return;
		} else {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row == null) {
				return;
			} else {
				for(int i = 0; i <= row.getLastCellNum(); i++) {
					XSSFCell cell = row.getCell(i);
					if(cell != null) {
						XSSFCellStyle style = wb.createCellStyle();
						style.cloneStyleFrom(cell.getCellStyle());
						cell.setCellStyle(this.getErrRowHSSFCellStyle(style));
					}
				}
			}
		}
	}
	
	private XSSFCellStyle getErrRowHSSFCellStyle(XSSFCellStyle style) {
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
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
