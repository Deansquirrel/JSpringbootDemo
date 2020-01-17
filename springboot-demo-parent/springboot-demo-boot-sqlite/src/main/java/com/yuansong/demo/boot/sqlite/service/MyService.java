package com.yuansong.demo.boot.sqlite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.demo.boot.sqlite.dao.UserMapper;

@Service
public class MyService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public void sTest() {
		logger.debug("=================================");
		logger.debug("server test");
//		this.myRepository.rTest();
		Gson gson = new Gson();
		logger.debug(gson.toJson(this.userMapper.queryById(0)));
		logger.debug("=================================");
	}

}
