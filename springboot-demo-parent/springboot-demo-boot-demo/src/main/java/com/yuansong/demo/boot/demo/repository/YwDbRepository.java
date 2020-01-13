package com.yuansong.demo.boot.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class YwDbRepository {
	
	private static final String SQL_getDyp = "select dypkey,dypvalue from vw_dtv5yuanparams_callback";
	
	@Autowired
	@Qualifier("jdbcTemplateDefault")
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getDyp() {
		if(this.jdbcTemplate == null) {
			throw new RuntimeException("yw db connection is null");
		}
		return this.jdbcTemplate.queryForList(SQL_getDyp);
	}

}
