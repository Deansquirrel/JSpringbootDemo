package com.yuansong.demo.boot.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yuansong.demo.boot.jwt.utils.JwtUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"02 Jwt"})
@RequestMapping("/jwt")
public class JwtController {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtController.class);
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String jwt() {
		logger.debug("jwt");
		return "jwt";
	}
	
	@GetMapping("/get")
	@ApiOperation("路径测试")
	public String jwtGet(String id, String subject, long ttlMillis) {
		logger.debug("jwt get");
		return JwtUtils.createJWT(id, subject, ttlMillis);
	}
	
	@GetMapping("/validate")
	@ApiOperation("路径测试")
	public String jwtValidate(String jwt) {
		logger.debug("jwt validate");
		Gson gson = new Gson();
		return gson.toJson(JwtUtils.validateJWT(jwt));
	}

}
