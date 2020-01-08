package com.yuansong.demo.boot.jtoolstest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuansong.demo.boot.jtoolstest.controller.VO.CommonVO;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public CommonVO exceptionHandler(HttpServletRequest req, Exception e) {
		logger.error(e.getMessage());
		return new CommonVO(-1,e.getMessage());
	}

}
