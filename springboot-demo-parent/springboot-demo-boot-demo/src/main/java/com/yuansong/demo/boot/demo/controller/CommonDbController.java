package com.yuansong.demo.boot.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.demo.boot.demo.service.CommonDbDynamicDataSourceTestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"02 Common Db"})
@RequestMapping("/commonDb")
public class CommonDbController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDbController.class);
	
	@Autowired
	private CommonDbDynamicDataSourceTestService commonDbDynamicDataSourceTestService;
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String commonDb() {
		logger.debug("commonDb");
		return "commonDb";
	}
	
	@GetMapping("/subTest")
	@ApiOperation("subTest")
	public String subTest() {
		this.commonDbDynamicDataSourceTestService.subTest();
		return "OK";
	}

}
