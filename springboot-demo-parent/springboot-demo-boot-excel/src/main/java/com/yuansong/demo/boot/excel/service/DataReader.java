package com.yuansong.demo.boot.excel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuansong.demo.boot.excel.DTO.BaseData;

@Component
public class DataReader {
	
//	private static final Logger logger = LoggerFactory.getLogger(DataReader.class);
	
	@Autowired
	private Message msg;
	
	public List<BaseData> getData(XSSFWorkbook wb, String sheetName, int colIndex1, int colIndex2) throws Exception {
		List<BaseData> list = new ArrayList<BaseData>();
		XSSFSheet sheet = wb.getSheet(sheetName);
		if(sheet == null) {
			String errMsg = "不存在名称为【" + sheetName + "】的sheet";
			this.msg.print(errMsg);
			throw new Exception(errMsg);
		}
		BaseData d = null;
		for(int i = 1; i <= sheet.getLastRowNum(); i++) {
			d = new BaseData();
			d.setRowIndex(i);
			d.setRwm(this.getStringVal(sheet, i, colIndex1));
			d.setDs(this.getStringVal(sheet, i, colIndex2));
			list.add(d);
		}
		return list;
	}

	private String getStringVal(XSSFSheet sheet, Integer row, Integer col) {
		sheet.getRow(row).getCell(col).setCellType(CellType.STRING);
		String data = sheet.getRow(row).getCell(col).getStringCellValue();
		data = data.trim();
		if(data == null || "".equals(data)) {
			return null;
		} else {
			return data;
		}
	}
	
	public XSSFWorkbook getXSSFWorkbook(String filePath) throws Exception {
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			this.msg.print("文件路径：" + filePath);
			File destFile = new File(filePath);
			if(destFile.exists()) {
				in = new FileInputStream(destFile);
				wb = new XSSFWorkbook(in);
				return wb;
			} else {
				this.msg.print("数据文件不存在");
				return null;
			}
		} catch(Exception e) {
			this.msg.print("读取数据文件时发生异常");
			throw e;
		}
	}
}
