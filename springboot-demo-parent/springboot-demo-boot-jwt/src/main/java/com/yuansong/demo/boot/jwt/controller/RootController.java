package com.yuansong.demo.boot.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.demo.boot.jwt.utils.JWT_ERRCODE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"01 Root"})
@RequestMapping("/")
public class RootController {
	
	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String root() {
		logger.debug("root");
		logger.debug(String.valueOf(JWT_ERRCODE.SUCCESS));
		return "root";
	}
	
	@GetMapping("/hello")
	@ApiOperation("路径测试")
	public String hello() {
		logger.debug("hello");
		return "hello";
	}

}
