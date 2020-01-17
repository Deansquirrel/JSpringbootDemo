package com.yuansong.demo.boot.sqlite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.demo.boot.sqlite.service.MyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"02 Test"})
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private MyService myService;
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String testRoot() {
		logger.debug("test root");
		this.myService.sTest();
		return "test root";
	}
}
