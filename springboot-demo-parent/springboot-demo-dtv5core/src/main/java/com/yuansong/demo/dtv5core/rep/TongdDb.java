package com.yuansong.demo.dtv5core.rep;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class TongdDb {
	
	private static final Logger logger = LoggerFactory.getLogger(TongdDb.class);
	
//	@Autowired
//	private DataSourceHelper dataSourceHelper;
	
	@Autowired
	private DynamicDataSourceContextHolder dynamicDataSourceContextHolder; 
	
	@Autowired	
	private DynamicRoutingDataSource dynamicRoutingDataSource;
	
	@Bean(name="dsTongd")
	public DataSource dynamicDataSource() {
		return this.dynamicRoutingDataSource;
	}
	
	public JdbcTemplate jdbcTemplateTongd(@Qualifier("dsTongd") DataSource ds) {
		if(this.dynamicRoutingDataSource.length() > 0) {
			return new JdbcTemplate(ds);
		} else {
			return null;
		}
	}
	
	@Bean(name="txManagerTongd")
	public PlatformTransactionManager transactionManager(@Qualifier("dsTongd") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
	
	/**
	 * 初始化通道库连接
	 * 
	 * @throws Exception
	 */
	public void initDbInfo() throws Exception {
		logger.debug("initDbInfo");
		for(int i = 0; i <=2; i++) {
			this.dynamicRoutingDataSource.addDataSource("tongd" + String.valueOf(i) , null);			
		}
	}
	
	public void setTongdIndex(int index) {
		this.dynamicDataSourceContextHolder.setDataSourceKey("tongd" + String.valueOf(index));
	}
}
