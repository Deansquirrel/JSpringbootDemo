package com.yuansong.demo.common.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.yuansong.demo.common.config.db.DataSourceConfigDefault;

@Component
public class DataSourceBean {
	
	@Autowired
	private DataSourceConfigDefault dataSourceConfigDefault;
	
	@Autowired
	private DataSourceHelper dataSourceHelper;
	
	@Bean(name="dsDefault")
	public DataSource dsDefault() {
		if(this.dataSourceConfigDefault != null) {
			return this.dataSourceHelper.getDataSourceByConfig(this.dataSourceConfigDefault);
		} else {
			return null;
		}
	}
	
	@Bean(name="jdbcTemplateDefault")
	@Primary
	public JdbcTemplate jdbcTemplateDefault(@Qualifier("dsDefault") DataSource ds) {
		return new JdbcTemplate(ds);
	}

}
