package com.yuansong.demo.boot.dtv5coretest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.demo.dtv5core.dto.DTCHLujDto;

@Service
public class YwService {
	
	private static final Logger logger = LoggerFactory.getLogger(YwService.class);
	
	@Autowired
	private TongdService tongdService;
	
	@Autowired
	private YuanService yuanService;

	public void sTest() throws Exception {
		Gson gson = new Gson();
		for(DTCHLujDto dto : this.tongdService.GetDTCHLujListByYuan(0, this.yuanService.getTongdChanCode(), "gongs", -101)) {
			logger.debug("===========================================");
			logger.debug(gson.toJson(dto));
			logger.debug("===========================================");
		};
		
//		Connection conn = this.ywDbRepository.getConnection();
//		this.RequestSendOfferTest(conn, pmChan, pmMubXitLeix, pmMubXitID, atom);
//		Gson gson = new Gson();
//		try {
//			//执行业务
////			for(int i = 0; i < 0; i++) {
////				logger.debug(String.valueOf(i) + " - " + this.getNewShijCungno());
////				Thread.sleep(1000);
////			}
//			logger.debug("=============================================");
//			List<JgInfo> wlzxList = this.ywDbRepository.GetJgList_wlzx(true);
//			for(JgInfo info : wlzxList) {
//				logger.debug(gson.toJson(info));
//			}
//			logger.debug("=============================================");
//			
//			logger.debug("=============================================");
//		} catch(Exception e) {
//			throw e;
//		} finally {
//			this.ReleaseSendOffer(conn, pmChan, pmMubXitLeix, pmMubXitID, atom);
//			conn.setAutoCommit(true);
//			conn.close();			
//		}
	}
	
}
