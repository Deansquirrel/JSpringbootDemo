package com.yuansong.demo.dtv5core.rep;

import org.springframework.stereotype.Component;

@Component
public class DynamicDataSourceContextHolder {
	
	private final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "dynamic_db0";
		}
	};
	
	public void setDataSourceKey(String key) {
		contextHolder.set(key);
	}
	
	public String getDataSourceKey() {
		return contextHolder.get();
	}
	
	public void clearDataSourceKey() {
		contextHolder.remove();
	}

}
