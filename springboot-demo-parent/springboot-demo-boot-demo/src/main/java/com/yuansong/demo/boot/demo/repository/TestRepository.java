package com.yuansong.demo.boot.demo.repository;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yuansong.demo.common.config.db.DataSourceConfig;
import com.yuansong.demo.common.db.DataSourceHelper;
import com.yuansong.demo.common.db.dynamic.DynamicRoutingDataSource;

@Repository
public class TestRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TestRepository.class);
	
//	private static final String SQL_dbList = "SELECT [name] FROM master..sysdatabases";
	private static final String SQL_dbCount = "SELECT COUNT(*) FROM master..sysdatabases";
	
	@Autowired
	private DataSourceHelper dataSourceHelper;
	
//	@Autowired
//	@Qualifier("tongdDynamicDataSourceContextHolder")
//	private DynamicDataSourceContextHolder dynamicDataSourceContextHolder;
	
	@Autowired
	@Qualifier("tongdDynamicRoutingDataSource")
	private DynamicRoutingDataSource dynamicRoutingDataSource;
	
	@Autowired
	@Qualifier("tongdJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public void subTest() {
		logger.debug("=========================================");
		logger.debug("add dataSource");
		this.dynamicRoutingDataSource.addDataSource("ds1", this.getDataSourceOne());
		this.dynamicRoutingDataSource.addDataSource("ds2", this.getDataSourceTwo());
		this.dynamicRoutingDataSource.addDataSource("ds3", this.getDataSourceThree());
		this.dynamicRoutingDataSource.addDataSource("ds4", this.getDataSourceFour());
		logger.debug("=========================================");
		logger.debug(String.valueOf(this.dynamicRoutingDataSource.size()));
		logger.debug("=========================================");
		this.dynamicRoutingDataSource.release();
		this.dynamicRoutingDataSource.setDataSourceKey("ds1");
		logger.debug(String.valueOf(this.jdbcTemplate.queryForObject(SQL_dbCount, Integer.class)));
		this.dynamicRoutingDataSource.release();
		this.dynamicRoutingDataSource.setDataSourceKey("ds2");
		logger.debug(String.valueOf(this.jdbcTemplate.queryForObject(SQL_dbCount, Integer.class)));
		this.dynamicRoutingDataSource.setDataSourceKey("ds3");
		logger.debug(String.valueOf(this.jdbcTemplate.queryForObject(SQL_dbCount, Integer.class)));
		logger.debug("=========================================");
	}
	
	private DataSource getDataSourceOne() {
		DataSourceConfig config = new DataSourceConfig();
		config.setName("ds1");
		config.setUrl("jdbc:sqlserver://192.168.5.1:2003;DatabaseName=master");
		config.setUsername("sa");
		config.setPassword("");
		return this.dataSourceHelper.getDataSourceByConfig(config);
	}
	
	private DataSource getDataSourceTwo() {
		DataSourceConfig config = new DataSourceConfig();
		config.setName("ds2");
		config.setUrl("jdbc:sqlserver://192.168.5.1:2004;DatabaseName=master");
		config.setUsername("sa");
		config.setPassword("");
		return this.dataSourceHelper.getDataSourceByConfig(config);
	}
	
	private DataSource getDataSourceThree() {
		DataSourceConfig config = new DataSourceConfig();
		config.setName("ds3");
		config.setUrl("jdbc:sqlserver://192.168.5.1:2005;DatabaseName=master");
		config.setUsername("sa");
		config.setPassword("");
		return this.dataSourceHelper.getDataSourceByConfig(config);
	}
	
	private DataSource getDataSourceFour() {
		DataSourceConfig config = new DataSourceConfig();
		config.setName("ds4");
		config.setUrl("jdbc:sqlserver://192.168.5.1:2006;DatabaseName=master");
		config.setUsername("sa");
		config.setPassword("");
		return this.dataSourceHelper.getDataSourceByConfig(config);
	}


}
