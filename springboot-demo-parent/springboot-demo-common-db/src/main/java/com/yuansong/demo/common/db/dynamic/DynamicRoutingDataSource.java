package com.yuansong.demo.common.db.dynamic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private DynamicDataSourceContextHolder contextHolder = null;
	private Map<Object, Object> targetDataSources = null;
	
	private DynamicRoutingDataSource() {}
	
	public static final DynamicRoutingDataSource createDynamicRoutingDataSource(DynamicDataSourceContextHolder contextHolder) {
		DynamicRoutingDataSource drds = new DynamicRoutingDataSource();
		drds.setTargetDataSources(new HashMap<>());
		drds.contextHolder = contextHolder;
		return drds;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		logger.debug("Current DataSource is [{}]", this.contextHolder.getDataSourceKey());
		return this.contextHolder.getDataSourceKey();
	}
	
	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		this.targetDataSources = targetDataSources;
	}
	
	public boolean isExistDataSource(String key) {
		return this.targetDataSources.containsKey(key);
	}
	
	public int size() {
		return this.targetDataSources.size();
	}
	
	public synchronized void addDataSource(String key, DataSource dataSource) {
		this.targetDataSources.put(key, dataSource);
//		if(this.size() == 1) {
//			this.setDefaultTargetDataSource(dataSource);
//		}
		this.afterPropertiesSet();
		logger.debug("datasource {} has been added", dataSource);
	}
	
	public synchronized void removeDataSource(String key) {
		DataSource dataSource = (DataSource) this.targetDataSources.get(key);
		this.targetDataSources.remove(key);
		this.afterPropertiesSet();
		logger.debug("datasource {} has been removed", dataSource);
	}
	
	public synchronized void setDataSourceKey(String key) {
		this.contextHolder.setDataSourceKey(key);
	}
	
	public synchronized void clearDataSourceKey() {
		this.contextHolder.clearDataSourceKey();
	}
	
	public Set<String> keySet() {
		Set<String> result = new HashSet<String>();
		for(Object key : this.targetDataSources.keySet()) {
			result.add(String.valueOf(key));
		}
		return result;
	}
	
	public synchronized void clear() {
		for(String key : this.keySet()) {
			this.removeDataSource(key);
		}
	}
	
}
