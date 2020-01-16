package com.yuansong.demo.boot.dtv5coretest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.demo.boot.dtv5coretest.repository.TongdRepository;
import com.yuansong.demo.dtv5core.DTV5CoreTongd;
import com.yuansong.demo.dtv5core.DTV5CoreYuan;
import com.yuansong.demo.dtv5core.dto.DTCHLujDto;
import com.yuansong.demo.dtv5core.dto.TongdInfoDto;

@Service
public class TongdService {
	
	private static final Logger logger = LoggerFactory.getLogger(TongdService.class);

	@Autowired
	private TongdRepository tongdRepository;
	
	@Autowired
	private DTV5CoreTongd dtv5CoreTongd;
	
	public List<DTCHLujDto> GetDTCHLujListByYuan(int tongdIndex, String tongdChanCode, String yuanXitLeix, long yuanXitID) throws Exception {
		String dbKey = "tongd" + String.valueOf(tongdIndex);
		logger.debug("db: " + dbKey);
		this.tongdRepository.setDataSourceKey(dbKey);
		try {
			return this.dtv5CoreTongd.GetDTCHLujListByYuan(tongdChanCode, yuanXitLeix, yuanXitID);
		} finally {
			this.tongdRepository.clearDataSourceKey();
		}
	}
	
}
