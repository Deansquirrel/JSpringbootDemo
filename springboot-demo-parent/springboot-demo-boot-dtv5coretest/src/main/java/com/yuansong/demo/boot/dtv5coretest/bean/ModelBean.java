package com.yuansong.demo.boot.dtv5coretest.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.yuansong.demo.dtv5core.DTV5CoreTongd;
import com.yuansong.demo.dtv5core.DTV5CoreYuan;

@Component
public class ModelBean {
	
	@Autowired
	@Qualifier("jdbcTemplateDefault")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("tongdJdbcTemplate")
	private JdbcTemplate tongdJdbcTemplate;
	
	@Bean
	public DTV5CoreYuan getDTV5CoreYuan(@Qualifier("jdbcTemplateDefault") JdbcTemplate jdbcTemplate) {
		return new DTV5CoreYuan(jdbcTemplate); 
	}
	
	@Bean
	public DTV5CoreTongd getDTV5CoreTongd(@Qualifier("tongdJdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new DTV5CoreTongd(jdbcTemplate);
	}

}
