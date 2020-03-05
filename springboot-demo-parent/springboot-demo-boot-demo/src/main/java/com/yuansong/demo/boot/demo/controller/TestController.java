package com.yuansong.demo.boot.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"02 Test"})
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String testRoot() {
		logger.debug("root");
		return "root";
	}
	
	@GetMapping("/id")
	@ApiOperation("路径测试")
	public String testId() {
		logger.debug("test id");
		
		logger.debug(Integer.toBinaryString(86399));
		
		logger.debug(String.valueOf(Long.parseLong("101010001011111111111111111111111111111", 2)));
		
		
//		SnowFlakeIdGenerator sf = new SnowFlakeIdGenerator(1,5);
		
//		StringBuilder sb = new StringBuilder();
//		long c = System.currentTimeMillis();
//		do {			
//			sb.append(Long.toBinaryString(sf.getNextId()));
//			sb.append("\n");
//		} while (System.currentTimeMillis() - c < 2000);
//		logger.debug(sb.toString());
		
		
		
		return "test id";
	}
	
}
