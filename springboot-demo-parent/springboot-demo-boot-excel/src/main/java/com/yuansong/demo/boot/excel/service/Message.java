package com.yuansong.demo.boot.excel.service;

import org.springframework.stereotype.Component;

import com.yuansong.tools.common.DateTool;

@Component
public class Message {
	
	private DateTool dt = new DateTool();
	
	public void print(String msg) {
		System.out.println(dt.GetDatetimeStr() + " " + msg);
	}

}
