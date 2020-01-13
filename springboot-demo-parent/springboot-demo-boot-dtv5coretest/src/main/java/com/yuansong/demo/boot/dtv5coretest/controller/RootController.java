package com.yuansong.demo.boot.dtv5coretest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.demo.dtv5core.rep.TongdDb;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"01 Root"})
@RequestMapping("/")
public class RootController {
	
	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@Autowired
	private TongdDb tongdDb;
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String root() {
		logger.debug("root");
		return "root";
	}
	
	@GetMapping("/hello")
	@ApiOperation("路径测试")
	public String hello() {
		logger.debug("hello");
		
		try {
			this.tongdDb.initDbInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tongdDb.setTongdIndex(0);
		
		return "hello";
	}
}
