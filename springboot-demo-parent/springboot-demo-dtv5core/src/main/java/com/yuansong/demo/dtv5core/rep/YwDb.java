package com.yuansong.demo.dtv5core.rep;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.yuansong.demo.common.config.db.DataSourceConfigDefault;
import com.yuansong.demo.common.db.DataSourceHelper;

@Component
public class YwDb {
	
	@Autowired
	@Qualifier("DataSourceConfigDefault")
	private DataSourceConfigDefault dataSourceConfigDefault;
	
	@Autowired
	private DataSourceHelper dataSourceHelper;
	
	@Bean(name="dsDefault")
	public DataSource dsDefault() {
		if(this.dataSourceConfigDefault != null) {
			this.dataSourceConfigDefault.setName("ywdb");
			return this.dataSourceHelper.getDataSourceByConfig(this.dataSourceConfigDefault);
		} else {
			return null;
		}
	}

	@Bean(name="jdbcTemplateDefault")
	@Primary
	public JdbcTemplate jdbcTemplateDafault(@Qualifier("dsDefault") DataSource ds) {
		if(ds != null) {
			return new JdbcTemplate(ds);
		} else {
			return null;
		}
	}
	
	@Bean(name="txManagerDefault")
	public PlatformTransactionManager txManager(@Qualifier("dsSecond") DataSource ds) {
		if(ds != null) {
			return new DataSourceTransactionManager(ds);
		} else {
			return null;
		}
	}
	
}
