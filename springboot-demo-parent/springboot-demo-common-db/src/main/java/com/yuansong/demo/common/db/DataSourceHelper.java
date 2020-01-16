package com.yuansong.demo.common.db;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.yuansong.demo.common.config.db.DataSourceConfig;

@Component
public class DataSourceHelper {

	public <T extends DataSourceConfig> DataSource getDataSourceByConfig(T config) {
		DruidDataSource ds = new DruidDataSource();
		
		if(config.getName() != null && config.getName().trim() != "") {
			ds.setName(config.getName().trim());
		}
		if(config.getUrl() == null || config.getUrl().trim() == "") {
			throw new RuntimeException("Datasource url can not be null");
		} else {
			ds.setUrl(config.getUrl().trim());
		}
		if(config.getUsername() == null || config.getUsername().trim() == "") {
			throw new RuntimeException("Datasource username can not be null");
		} else {
			ds.setUsername(config.getUsername().trim());
		}
		if(config.getPassword() != null) {
			ds.setPassword(config.getPassword());
		}
		if(config.getMinConn() == null || config.getMinConn() < 0) {
			config.setMinConn(0);
		}
		ds.setMinIdle(config.getMinConn());
		ds.setInitialSize(config.getMinConn());
		if(config.getMaxConn() == null || config.getMaxConn() < 1) {
			config.setMaxConn(30);
		}
		ds.setMaxActive(config.getMaxConn());
		if(config.getMaxWait() == null || config.getMaxWait() < 1000) {
			config.setMaxWait(10000);
		}
		ds.setMaxWait(config.getMaxWait());
		if(config.getQueryTimeout() == null || config.getQueryTimeout() < 0) {
			config.setQueryTimeout(3600);
		}
		ds.setQueryTimeout(config.getQueryTimeout());
		if(config.getValidationQuery() == null || config.getValidationQuery().trim() == "") {
			config.setValidationQuery("SELECT 'X'");
		}
		ds.setValidationQuery(config.getValidationQuery());
		ds.setTimeBetweenEvictionRunsMillis(60000);
		ds.setMinEvictableIdleTimeMillis(30000);
		
		int connectionErrorRetryAttempts = ds.getConnectionErrorRetryAttempts();
		boolean breakAfterAcquireFailure = ds.isBreakAfterAcquireFailure();
		ds.setConnectionErrorRetryAttempts(0);
		ds.setBreakAfterAcquireFailure(true);
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			jdbcTemplate.queryForObject("select 'X'", String.class);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			ds.setConnectionErrorRetryAttempts(connectionErrorRetryAttempts);
			ds.setBreakAfterAcquireFailure(breakAfterAcquireFailure);		
		}

		return ds;
	}

}
