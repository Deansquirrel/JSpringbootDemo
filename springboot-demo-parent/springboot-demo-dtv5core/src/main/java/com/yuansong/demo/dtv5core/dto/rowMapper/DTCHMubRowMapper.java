package com.yuansong.demo.dtv5core.dto.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.demo.dtv5core.dto.DTCHLujDto;

public class DTCHMubRowMapper implements RowMapper<DTCHLujDto> {

	@Override
	public DTCHLujDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		DTCHLujDto r = new DTCHLujDto();
		r.setYuanXitLeix(rs.getString("yuanxitleix"));
		r.setYuanXitID(rs.getInt("yuanxitid"));
		r.setChan(rs.getString("chan"));
		r.setMubXitLeix(rs.getString("mubxitleix"));
		r.setMubXitID(rs.getInt("mubxitid"));
		return r;
	}

}
