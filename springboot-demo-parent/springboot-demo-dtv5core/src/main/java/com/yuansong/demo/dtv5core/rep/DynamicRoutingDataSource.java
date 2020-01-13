package com.yuansong.demo.dtv5core.rep;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);
	
	private Map<Object,Object> targetDataSource = new ConcurrentHashMap<>();
	
	@Autowired
	private DynamicDataSourceContextHolder dynamicDataSourceContextHolder;

	@Override
	protected Object determineCurrentLookupKey() {
		logger.debug("Current DataSource is [{}]", this.dynamicDataSourceContextHolder.getDataSourceKey());
		return this.dynamicDataSourceContextHolder.getDataSourceKey();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		this.targetDataSource = targetDataSources;
	}
	
	/**
	 * 是否存在当前key的 DataSource
	 * 
	 * @param key
	 * @return
	 */
	public boolean isExistDataSource(String key) {
		return this.targetDataSource.containsKey(key);
	}
	
	/**
	 * 添加数据源
	 * 
	 * @param key
	 * @param dataSource
	 */
	public synchronized void addDataSource(String key, DataSource dataSource) {
		this.targetDataSource.put(key, dataSource);
		logger.debug("DataSource {} has been added.",key);
	}
	
	/**
	 * 数据源长度
	 * 
	 * @return
	 */
	public int length() {
		return this.targetDataSource.size();
	}

}
