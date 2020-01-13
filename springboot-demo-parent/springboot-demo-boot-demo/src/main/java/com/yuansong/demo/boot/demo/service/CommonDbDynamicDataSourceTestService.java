package com.yuansong.demo.boot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuansong.demo.boot.demo.repository.TestRepository;

@Service
public class CommonDbDynamicDataSourceTestService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDbDynamicDataSourceTestService.class);
	
	
	@Autowired
	private TestRepository testRepository;
	
	public void subTest() {
		logger.debug("subTest");
		this.testRepository.subTest();
	}
	
	
}
