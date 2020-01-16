package com.yuansong.demo.boot.dtv5coretest.repository.dto.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.demo.boot.dtv5coretest.repository.dto.JgInfo;

public class JgInfoRowMapper implements RowMapper<JgInfo> {

	@Override
	public JgInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		JgInfo info = new JgInfo();
		info.setId(rs.getInt("fzjgid"));
		info.setXsMc(rs.getString("fzjgxsmc"));
		info.setLx(rs.getInt("fzjglx"));
		info.setPym(rs.getString("fzjgpym"));
		info.setSym(rs.getString("fzjgsym"));
		info.setTdm(rs.getString("fzjgtdm"));
		info.setXsm(rs.getString("fzjgxsm"));
		info.setForbidden(rs.getInt("fzjgisforbidden") == 1 ? true : false);
		return info;
	}

}
