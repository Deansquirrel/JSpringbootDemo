package com.yuansong.demo.boot.dtv5coretest.service;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.demo.boot.dtv5coretest.repository.YwDbRepository;
import com.yuansong.demo.dtv5core.DTV5CoreYuan;
import com.yuansong.demo.dtv5core.dto.TongdInfoDto;


@Service
public class YuanService {
	
	private static final Logger logger = LoggerFactory.getLogger(YuanService.class);
	
	private boolean hasTongdInit = false;
	private int tongdCount = 0;
	private String tongdChanCode = "";
	
	@Autowired
	private YwDbRepository ywDbRepository;
	
	@Autowired
	private DTV5CoreYuan dtv5CoreYuan;
	
	/**
	 * 获取一个事件存根号
	 * 
	 * @return
	 * @throws Exception 
	 */
	@Transactional(value = "txManagerDefault", rollbackFor=Exception.class)
	public String getNewShijCungno() throws Exception {
		return this.dtv5CoreYuan.GetNewShijCungno();
	}
	
//	public void RequestSendOffer(String pmChan, String pmMubXitLeix, long pmMubXitID, int atom, String yuany) throws Exception {
//		this.ywDbRepository.RequestSendOffer(conn, pmChan, pmMubXitLeix, pmMubXitID, atom, yuany);
//	}
//	
//	public void RequestSendOffer(Connection conn, String pmChan, String pmMubXitLeix, long pmMubXitID, int atom) throws Exception {
//		this.ywDbRepository.RequestSendOffer(conn, pmChan, pmMubXitLeix, pmMubXitID, atom);
//	}
//	
//	@Transactional(value = "txManagerDefault", rollbackFor=Exception.class)
//	public void ReleaseSendOffer(String pmChan, String pmMubXitLeix, long pmMubXitID, int atom) throws Exception {
//		this.ywDbRepository.ReleaseSendOffer(pmChan, pmMubXitLeix, pmMubXitID, atom);
//	}
//	
//	@Transactional(value = "txManagerDefault", rollbackFor=Exception.class)
//	public void RequestSendOfferTest(Connection conn, String pmChan, String pmMubXitLeix, long pmMubXitID, int atom) throws Exception {
//		this.ywDbRepository.RequestSendOfferTest(conn, pmChan, pmMubXitLeix, pmMubXitID, atom);
//	}
	
	public void RequestSendOfferTest(Connection conn, String pmChan, String pmMubXitLeix, long pmMubXitID, int atom) throws Exception {
		conn.setAutoCommit(false);
		try {
			this.dtv5CoreYuan.RequestSendOffer(conn, pmChan, pmMubXitLeix, pmMubXitID, atom, "");
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}
	
	public void ReleaseSendOffer(Connection conn, String pmChan, String pmMubXitLeix, long pmMubXitID, int atom) throws Exception {
		conn.setAutoCommit(false);
		try {
			this.dtv5CoreYuan.ReleaseSendOffer(conn, pmChan, pmMubXitLeix, pmMubXitID, atom);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public void BiandShij(String pmChan, String pmMubXitLeix, long pmMubXitID, String pmJiezhShijCungno) throws Exception {
		this.dtv5CoreYuan.BiandShij(pmChan, pmMubXitLeix, pmMubXitID, pmJiezhShijCungno);
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public void InitTongdParam() throws Exception {
		if(this.hasTongdInit) {
			return;
		}
		TongdInfoDto dto = this.dtv5CoreYuan.GetTongdInfo();
		logger.debug("==============================================");
		logger.debug("InitTongdParam");
		logger.debug(dto.getTongdChanCode());
		logger.debug(String.valueOf(dto.getTongdCount()));
		for(Integer key : dto.getTongdConn().keySet()) {
			logger.debug(String.valueOf(key) + " - " + dto.getTongdConn().get(key));
		}
		logger.debug("==============================================");
		this.hasTongdInit = true;
		this.tongdChanCode = dto.getTongdChanCode();
		this.tongdCount = dto.getTongdCount();
	}

	public String getTongdChanCode() throws Exception {
		if(!this.hasTongdInit) {
			this.InitTongdParam();
		}
		return tongdChanCode;
	}

	public int getTongdCount() throws Exception {
		if(!this.hasTongdInit) {
			this.InitTongdParam();
		}
		return tongdCount;
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public int GetJgId()  throws Exception {
		return this.ywDbRepository.GetJgId();
	}
}
