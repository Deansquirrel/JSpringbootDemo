package com.yuansong.demo.boot.sqlite.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(MyRepository.class);
	
	public void rTest() {
		logger.debug("===================================================");
		logger.debug("r test");
		logger.debug("===================================================");
	}

}
