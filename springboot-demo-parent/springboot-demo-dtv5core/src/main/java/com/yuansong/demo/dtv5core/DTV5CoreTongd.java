package com.yuansong.demo.dtv5core;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yuansong.demo.dtv5core.dto.DTCHLujDto;
import com.yuansong.demo.dtv5core.dto.rowMapper.DTCHMubRowMapper;

public class DTV5CoreTongd {
	
	private JdbcTemplate jdbcTemplate = null;
	
	public DTV5CoreTongd(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 获取DTCH传出目标路径清单
	 * [待修改] private 返回 Collection
	 * 返回：包含路径清单的一个记录集。字段如下
	 * 	yuanxitleix nvarchar(255)
	 * 	yuanxitid int
	 * chan varchar(20)
	 * mubxitleix nvarchar(255)
	 * mubxitid int
	 * 
	 * @param pmTongdIndex
	 */
	public List<DTCHLujDto> GetDTCHLujListByYuan(String chanCode, String yuanXitLeix, long yuanXitID) throws Exception {
		return this.jdbcTemplate.query("exec pr_dtv5dtchlujlist_getbyyuan ?, ?, ?",
				new Object[] {chanCode, yuanXitLeix, yuanXitID},
				new DTCHMubRowMapper()); 
	};

}
