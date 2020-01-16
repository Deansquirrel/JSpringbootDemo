package com.yuansong.demo.boot.dtv5coretest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yuansong.demo.common.config.db.DataSourceConfig;
import com.yuansong.demo.common.db.DataSourceHelper;
import com.yuansong.demo.common.db.dynamic.DynamicRoutingDataSource;
import com.yuansong.demo.dtv5core.dto.TongdInfoDto;
import com.yuansong.tools.secret.ZillionWSDA;

@Repository
public class TongdRepository {
	
	@Autowired
	@Qualifier("tongdDynamicRoutingDataSource")
	private DynamicRoutingDataSource tongdDynamicRoutingDataSource;
	
	@Autowired
	private DataSourceHelper dataSourceHelper;
	
	public void setDataSourceKey(String dbKey) throws Exception {
		if(this.tongdDynamicRoutingDataSource.isExistDataSource(dbKey)) {
			this.tongdDynamicRoutingDataSource.setDataSourceKey(dbKey);			
		} else {
			throw new Exception("dbKey【" + dbKey + "】 is not exist");
		}
	}
	
	public void clearDataSourceKey() {
		this.tongdDynamicRoutingDataSource.clearDataSourceKey();
	}
	
	/**
	 * 初始化通道库连接信息
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void initTongdParam(TongdInfoDto dto) throws Exception {
		if(this.tongdDynamicRoutingDataSource.size() > 0) {
			this.tongdDynamicRoutingDataSource.clear();
		}
		for(String val : dto.getTongdConn().values()) {
			if(val == null || val.trim() == "") {
				continue;
			}
			String[] info = val.split("[|]",2);
			if(info.length != 2) {
				throw new Exception("tongd配置格式异常【" + val + "】");
			}
			ZillionWSDA zillionWSDA = new ZillionWSDA();
			String connInfoStr = zillionWSDA.DecryptFromBase64Format(info[1],info[0]);
			String[] connInfo = connInfoStr.split("[|]", 4);
			if(info.length != 2) {
				throw new Exception("tongd连接信息格式异常");
			}
			DataSourceConfig config = new DataSourceConfig();
			config.setName(info[0]);
			if(connInfo[0].contains(",")) {
				String[] connServer = connInfo[0].split(",", 2);
				config.setUrl(this.getMSSQLConnUrl(connServer[0], Integer.valueOf(connServer[1]), connInfo[1]));
			} else {
				config.setUrl(this.getMSSQLConnUrl(connInfo[0], 1433, connInfo[1]));
			}
			config.setUsername(connInfo[2]);
			config.setPassword(connInfo[3]);
			this.tongdDynamicRoutingDataSource.addDataSource(info[0], this.dataSourceHelper.getDataSourceByConfig(config));
		}
	}
	
	/**
	 * 获取数据库连接URL（MSSQL）
	 * 
	 * @param server
	 * @param port
	 * @param dbName
	 * @return
	 */
	private String getMSSQLConnUrl(String server, int port, String dbName) {
		return "jdbc:sqlserver://" + server  + " :" + String.valueOf(port)  + ";DatabaseName=" + dbName;
	}

}
