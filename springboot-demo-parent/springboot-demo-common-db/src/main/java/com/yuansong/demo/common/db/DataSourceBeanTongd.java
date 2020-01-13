package com.yuansong.demo.common.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.yuansong.demo.common.db.dynamic.DynamicDataSourceContextHolder;
import com.yuansong.demo.common.db.dynamic.DynamicRoutingDataSource;

@Component
public class DataSourceBeanTongd {
	
	@Bean(name = "tongdDynamicDataSourceContextHolder")
	public DynamicDataSourceContextHolder getDynamicDataSourceContextHolder() {
		return new DynamicDataSourceContextHolder();
	}
	
	@Bean(name = "tongdDynamicRoutingDataSource")
	public DynamicRoutingDataSource getDynamicRoutingDataSource(@Qualifier("tongdDynamicDataSourceContextHolder") DynamicDataSourceContextHolder dynamicDataSourceContextHolder) {
		return DynamicRoutingDataSource.createDynamicRoutingDataSource(dynamicDataSourceContextHolder);
	}
	
	@Bean(name="tongdJdbcTemplate")
	public JdbcTemplate jdbcTemplateSecond(@Qualifier("tongdDynamicRoutingDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	@Bean(name="tongdTxManagerSecond")
	public PlatformTransactionManager txManagerSecond(@Qualifier("tongdDynamicRoutingDataSource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
	

}
