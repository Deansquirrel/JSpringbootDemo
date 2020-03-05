package com.yuansong.demo.boot.excel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.yuansong.demo.boot.excel.DTO.CSGD;
import com.yuansong.demo.boot.excel.DTO.CSGJ;
import com.yuansong.demo.boot.excel.DTO.DHGD;
import com.yuansong.demo.boot.excel.DTO.DHGJ;
import com.yuansong.demo.boot.excel.DTO.JKGD;
import com.yuansong.demo.boot.excel.DTO.JKGJ;
import com.yuansong.tools.common.DateTool;

@Component
public class DataReader {
	
//	private static final Logger logger = LoggerFactory.getLogger(DataReader.class);
	
	@Autowired
	private Message msg;
	
	private DateTool dt = new DateTool();
	
	/**
	 * 读取传输告警数据
	 * @return
	 * @throws Exception
	 */
	public List<CSGJ> getCSGJData() throws Exception {
		List<CSGJ> list = new ArrayList<CSGJ>();
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			File destFile = new File(this.getDataPath() + "/传输告警.xlsx");
			if(destFile.exists()) {
				 in = new FileInputStream(destFile);
				 wb = new XSSFWorkbook(in);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int count = sheet.getLastRowNum();
				for(int i = 1; i <= count; i++) {
					CSGJ csgj = this.getCSGJFromSheet(sheet, i);
					//忽略子告警
					if(csgj.getGjbt() != null && !csgj.getGjbt().trim().startsWith("|---")) {
						list.add(csgj);
					}
				}
			} else {
				throw new Exception("传输告警 data is not exist");
			}
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {

				}
			}
		}
		return list;
	}
	
	/**
	 * 读取 传输告警 数据行
	 * @param sheet
	 * @param row
	 * @return
	 * @throws Exception
	 */
	private CSGJ getCSGJFromSheet(XSSFSheet sheet, Integer row) throws Exception {		
		CSGJ d = new CSGJ();
		
		int cCount = 0;
		
		d.setGjbt(this.getStringVal(sheet, row, cCount++));
		d.setGjfssj(this.getDateVal(sheet, row, cCount++));
		d.setGjqcsj(this.getDateVal(sheet, row, cCount++));
		d.setSfgjbzh(this.getBooleanVal(sheet, row, cCount++, null, "是", "否"));
		d.setZy(this.getStringVal(sheet, row, cCount++));
		d.setWymc(this.getStringVal(sheet, row, cCount++));
		d.setGjdxmc(this.getStringVal(sheet, row, cCount++));
		d.setGjcljb(this.getStringVal(sheet, row, cCount++));
		d.setDs(this.getStringVal(sheet, row, cCount++));
		d.setPdzt(this.getStringVal(sheet, row, cCount++));
		d.setGdzt(this.getStringVal(sheet, row, cCount++));
		d.setGdh(this.getStringVal(sheet, row, cCount++));
		d.setMdwy(this.getStringVal(sheet, row, cCount++));
		d.setGjgczt(this.getStringVal(sheet, row, cCount++));
		d.setYzpdyy(this.getStringVal(sheet, row, cCount++));
		d.setWggjlsh(this.getStringVal(sheet, row, cCount++));
		d.setWybm(this.getStringVal(sheet, row, cCount++));
		d.setSblx(this.getStringVal(sheet, row, cCount++));
		d.setGjdxlx(this.getStringVal(sheet, row, cCount++));
		d.setDwxx(this.getStringVal(sheet, row, cCount++));
		d.setGjly(this.getStringVal(sheet, row, cCount++));
		d.setGjfxsj(this.getDateVal(sheet, row, cCount++));
		d.setGjbzm(this.getStringVal(sheet, row, cCount++));
		d.setQx(this.getStringVal(sheet, row, cCount++));
		d.setGjlb(this.getStringVal(sheet, row, cCount++));
		d.setGjljfl(this.getStringVal(sheet, row, cCount++));
		d.setGjljzl(this.getStringVal(sheet, row, cCount++));
		d.setDlmc(this.getStringVal(sheet, row, cCount++));
		d.setPdgzmc(this.getStringVal(sheet, row, cCount++));
		d.setYxyhs(this.getIntegerVal(sheet, row, cCount++, null));
		d.setGdfqsj(this.getDateVal(sheet, row, cCount++));
		d.setGjzw(this.getStringVal(sheet, row, cCount++));
		d.setYclzt(this.getStringVal(sheet, row, cCount++));
		d.setGjjs(this.getStringVal(sheet, row, cCount++));
		d.setGjzt(this.getStringVal(sheet, row, cCount++));
		d.setCj(this.getStringVal(sheet, row, cCount++));
		d.setWygczt(this.getStringVal(sheet, row, cCount++));
		d.setWyId(this.getStringVal(sheet, row, cCount++));
		d.setSnwggjId(this.getStringVal(sheet, row, cCount++));
		d.setFzgjbs(this.getStringVal(sheet, row, cCount++));
		d.setGlbs(this.getStringVal(sheet, row, cCount++));
		d.setYjzyfl(this.getStringVal(sheet, row, cCount++));
		d.setEjzyfl(this.getStringVal(sheet, row, cCount++));
		d.setYwlx(this.getStringVal(sheet, row, cCount++));
		d.setYwxx(this.getStringVal(sheet, row, cCount++));
		d.setSfyddwyxg(this.getBooleanVal(sheet, row, cCount++, null, "是", "否"));
		return d;
	}
	
	/**
	 * 读取传输工单数据
	 * @return
	 * @throws Exception
	 */
	public List<CSGD> getCSGDData() throws Exception {
		List<CSGD> list = new ArrayList<CSGD>();
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			File destFile = new File(this.getDataPath() + "/传输工单.xlsx");
			if(destFile.exists()) {
				 in = new FileInputStream(destFile);
				 wb = new XSSFWorkbook(in);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int count = sheet.getLastRowNum();
				for(int i = 1; i <= count; i++) {
					list.add(this.getCSGDFromSheet(sheet, i));
				}
			} else {
				throw new Exception("传输工单 data is not exist");
			}
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {

				}
			}
		}
		return list;
	}
	
	/**
	 * 读取 传输工单 数据行
	 * @param sheet
	 * @param row
	 * @return
	 * @throws Exception
	 */
	private CSGD getCSGDFromSheet(XSSFSheet sheet, Integer row) throws Exception {
		
		CSGD d = new CSGD();
		
		int cCount = 0;
		
		d.setRwm(this.getStringVal(sheet, row, cCount++));
		d.setGdbh(this.getStringVal(sheet, row, cCount++));
		d.setGjlsh(this.getStringVal(sheet, row, cCount++));
		d.setGjly(this.getStringVal(sheet, row, cCount++));
		d.setGzfsds(this.getStringVal(sheet, row, cCount++));
		d.setFqr(this.getStringVal(sheet, row, cCount++));
		d.setFqsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setHfsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());		
		d.setGdzt(this.getStringVal(sheet, row, cCount++));
		d.setGzfssj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setGjqcsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setWysbcj(this.getStringVal(sheet, row, cCount++));
		d.setWlyjfl(this.getStringVal(sheet, row, cCount++));
		d.setWlejfl(this.getStringVal(sheet, row, cCount++));
		d.setWlsjfl(this.getStringVal(sheet, row, cCount++));
		d.setGzyyflyj(this.getStringVal(sheet, row, cCount++));
		d.setGzyyflej(this.getStringVal(sheet, row, cCount++));
		d.setT1clr(this.getStringVal(sheet, row, cCount++));
		d.setT1clrbm(this.getStringVal(sheet, row, cCount++));
		d.setClsfcst1(this.getBooleanVal(sheet, row, cCount++, null, "未超时", "超时"));
		d.setT1clzgz(this.getStringVal(sheet, row, cCount++));
		d.setT2clr(this.getStringVal(sheet, row, cCount++));
		d.setT2clrbm(this.getStringVal(sheet, row, cCount++));
		d.setClsfcst2(this.getBooleanVal(sheet, row, cCount++, null, "未超时", "超时"));
		d.setT2clzgz(this.getStringVal(sheet, row, cCount++));
		d.setT3clr(this.getStringVal(sheet, row, cCount++));
		d.setT3clrbm(this.getStringVal(sheet, row, cCount++));
		d.setClsfcst3(this.getBooleanVal(sheet, row, cCount++, null, "未超时", "超时"));
		d.setT3clzgz(this.getStringVal(sheet, row, cCount++));
		d.setGdr(this.getStringVal(sheet, row, cCount++));
		d.setGdgdsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setClcs(this.getStringVal(sheet, row, cCount++));
		d.setPdlx(this.getStringVal(sheet, row, cCount++));
		d.setGdlx(this.getStringVal(sheet, row, cCount++));
		d.setGjmc(this.getStringVal(sheet, row, cCount++));
		d.setGjfxsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setSfbht1hj(this.getBooleanVal(sheet, row, cCount++, null, "是", "否"));
		d.setT2sfyj(this.getBooleanVal(sheet, row, cCount++, null, "是", "否"));
		d.setT2sfbh(this.getBooleanVal(sheet, row, cCount++, null, "是", "否"));
		d.setZgjsl(this.getIntegerVal(sheet, row, cCount++, null));
		d.setCqcssj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setT2slr(this.getStringVal(sheet, row, cCount++));
		d.setT2slbm(this.getStringVal(sheet, row, cCount++));
		d.setT2slsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setDdt2hjsj(sheet.getRow(row).getCell(cCount++).getDateCellValue());
		d.setYclzt(this.getStringVal(sheet, row, cCount++));
		d.setYjmc(this.getStringVal(sheet, row, cCount++));
		d.setWymc(this.getStringVal(sheet, row, cCount++));
		d.setSfttgj(this.getStringVal(sheet, row, cCount++));
		return d;
	}
	
	/**
	 * 读取 动环告警数据
	 * @return
	 * @throws Exception
	 */
	public List<DHGJ> getDHGJData() throws Exception {
		List<DHGJ> list = new ArrayList<DHGJ>();
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			File destFile = new File(this.getDataPath() + "/动环告警.xlsx");
			if(destFile.exists()) {
				 in = new FileInputStream(destFile);
				 wb = new XSSFWorkbook(in);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int count = sheet.getLastRowNum();
				for(int i = 1; i <= count; i++) {
					DHGJ dhgj = this.getDHGJFromSheet(sheet, i);
					//忽略子告警
					if(dhgj.getGjbt() != null && !dhgj.getGjbt().trim().startsWith("|---")) {
						list.add(dhgj);
					}					
				}
			} else {
				throw new Exception("动环告警 data is not exist");
			}
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {

				}
			}
		}
		return list;
	}
	
	/**
	 * 读取 动环告警 数据行
	 * @param sheet
	 * @param row
	 * @return
	 */
	private DHGJ getDHGJFromSheet(XSSFSheet sheet, Integer row) {
		DHGJ d = new DHGJ();
		d.setGjbt(this.getStringVal(sheet, row, 0));
		d.setDs(this.getStringVal(sheet, row, 8));
		d.setGdh(this.getStringVal(sheet, row, 11));
		return d;
	}
	
	/**
	 * 读取动环工单数据
	 * @return
	 * @throws Exception
	 */
	public List<DHGD> getDHGDData() throws Exception {
		List<DHGD> list = new ArrayList<DHGD>();
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			File destFile = new File(this.getDataPath() + "/动环工单.xlsx");
			if(destFile.exists()) {
				 in = new FileInputStream(destFile);
				 wb = new XSSFWorkbook(in);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int count = sheet.getLastRowNum();
				for(int i = 1; i <= count; i++) {
					list.add(this.getDHGDFromSheet(sheet, i));
				}
			} else {
				throw new Exception("动环工单 data is not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {

				}
			}
		}
		return list;
	}
	
	/**
	 * 读取 动环工单 数据行
	 * @param sheet
	 * @param row
	 * @return
	 */
	private DHGD getDHGDFromSheet(XSSFSheet sheet, Integer row) {
		DHGD d = new DHGD();
		d.setRwm(this.getStringVal(sheet, row, 0));
		d.setGdbh(this.getStringVal(sheet, row, 1));
		d.setGzfsds(this.getStringVal(sheet, row, 4));
		return d;
	}
	
	/**
	 * 读取家客告警数据
	 * @return
	 * @throws Exception
	 */
	public List<JKGJ> getJKGJData() throws Exception {
		List<JKGJ> list = new ArrayList<JKGJ>();
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			File destFile = new File(this.getDataPath() + "/家客告警.xlsx");
			if(destFile.exists()) {
				 in = new FileInputStream(destFile);
				 wb = new XSSFWorkbook(in);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int count = sheet.getLastRowNum();
				for(int i = 1; i <= count; i++) {
					JKGJ jkgj = this.getJKGJFromSheet(sheet, i);
					//忽略子告警
					if(jkgj.getGjbt() != null && !jkgj.getGjbt().trim().startsWith("|---")) {
						list.add(jkgj);
					}
				}
			} else {
				throw new Exception("家客告警 data is not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {

				}
			}
		}
		return list;
	}
	
	/**
	 * 读取 家客告警数据行
	 * @param sheet
	 * @param row
	 * @return
	 */
	private JKGJ getJKGJFromSheet(XSSFSheet sheet, Integer row) {
		JKGJ d = new JKGJ();
		d.setGjbt(this.getStringVal(sheet, row, 0));
		d.setDs(this.getStringVal(sheet, row, 8));
		d.setGdh(this.getStringVal(sheet, row, 11));
		return d;
	}
	
	/**
	 * 读取家客工单数据
	 * @return
	 * @throws Exception
	 */
	public List<JKGD> getJKGDData() throws Exception {
		List<JKGD> list = new ArrayList<JKGD>();
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			File destFile = new File(this.getDataPath() + "/家客工单.xlsx");
			if(destFile.exists()) {
				 in = new FileInputStream(destFile);
				 wb = new XSSFWorkbook(in);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int count = sheet.getLastRowNum();
				for(int i = 1; i <= count; i++) {
					list.add(this.getJKGDFromSheet(sheet, i));
				}
			} else {
				throw new Exception("家客工单 data is not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {

				}
			}
		}
		return list;
	}
	
	/**
	 * 读取 家客工单 数据行
	 * @param sheet
	 * @param row
	 * @return
	 */
	private JKGD getJKGDFromSheet(XSSFSheet sheet, Integer row) {
		JKGD d = new JKGD();
		d.setRwm(this.getStringVal(sheet, row, 0));
		d.setGdbh(this.getStringVal(sheet, row, 1));
		d.setGzfsds(this.getStringVal(sheet, row, 4));
		return d;
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
	
	private Date getDateVal(XSSFSheet sheet, Integer row, Integer col) throws ParseException {
		sheet.getRow(row).getCell(col).setCellType(CellType.STRING);
		String data = sheet.getRow(row).getCell(col).getStringCellValue();
		data = data.trim();
		if(data == null || "".equals(data)) {
			return null;
		} else {
			try {
				return this.dt.ParseDatetimeStr(data);
			} catch (ParseException e) {
				String errMsg = "转换日期时发生错误，val " + data + " row " + String.valueOf(row) + " col " + String.valueOf(col);
				this.msg.print(errMsg);
				throw e;
			}
		}
	}
	
	private Integer getIntegerVal(XSSFSheet sheet, Integer row, Integer col, Integer defaultVal) throws NumberFormatException {
		String val = this.getStringVal(sheet, row, col);
		if(val == null || "".equals(val.trim())) {
			return defaultVal;
		}
		val = val.trim();
		try {
			return Integer.valueOf(val);
		} catch(NumberFormatException e) {
			String errMsg = "转换整形数据时发生错误，val " + val + " row " + String.valueOf(row) + " col " + String.valueOf(col);
			this.msg.print(errMsg);
			throw e;
		}
		
	}
	

	/**
	 * 
	 * @param sheet
	 * @param row
	 * @param col
	 * @param defaultVal 默认值
	 * @param strTrue
	 * @param strFalse
	 * @return
	 */
	private Boolean getBooleanVal(XSSFSheet sheet, Integer row, Integer col, Boolean defaultVal, String strTrue, String strFalse) {
		String val = this.getStringVal(sheet, row, col);
		if(val == null || "".equals(val.trim())) {
			return defaultVal;
		}
		val = val.trim();
		if(strTrue != null && strTrue.equals(val)) {
			return true;
		} else if (strFalse != null && strFalse.equals(val)) {
			return false;
		} else {
			return defaultVal;
		}
	}
	
	public String getDataPath() throws Exception {
		return ResourceUtils.getURL("data").getPath();
	}
	
}
