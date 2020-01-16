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
	public JdbcTemplate tongdJdbcTemplate(@Qualifier("tongdDynamicRoutingDataSource") DataSource ds) {
		if(ds != null) {
			return new JdbcTemplate(ds);			
		} else {
			return null;
		}
		
	}
	
	@Bean(name="tongdTxManager")
	public PlatformTransactionManager tongdTxManager(@Qualifier("tongdDynamicRoutingDataSource") DataSource ds) {
		if(ds != null) {
			return new DataSourceTransactionManager(ds);			
		} else {
			return null;
		}
		
	}
	

}
