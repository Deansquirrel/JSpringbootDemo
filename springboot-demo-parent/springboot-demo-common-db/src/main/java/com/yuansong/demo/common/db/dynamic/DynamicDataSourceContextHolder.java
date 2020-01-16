package com.yuansong.demo.common.db.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
	
	private final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "dynamic_dbo";
		}
	};
	
	public void setDataSourceKey(String key) {
		logger.debug("setDataSourceKey " + key);
		this.contextHolder.set(key);
	}
	
	public String getDataSourceKey() {
		return this.contextHolder.get();
	}
	
	public void clearDataSourceKey() {
		this.contextHolder.remove();
	}

}
