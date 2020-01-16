package com.yuansong.demo.boot.dtv5coretest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.demo.boot.dtv5coretest.controller.vo.BaseVO;
import com.yuansong.demo.boot.dtv5coretest.controller.vo.ErrorVO;
import com.yuansong.demo.boot.dtv5coretest.controller.vo.GetNewShijCungnoVO;
import com.yuansong.demo.boot.dtv5coretest.controller.vo.SuccessVO;
import com.yuansong.demo.boot.dtv5coretest.service.YuanService;
import com.yuansong.demo.boot.dtv5coretest.service.YwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dtv5test")
@Api(tags= {"02 DTV5TestController"})
public class DTV5TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(DTV5TestController.class);
	
	@Autowired
	private YwService ywService;
	
	@Autowired
	private YuanService yuanService;
	
	@GetMapping("/")
	@ApiOperation("路径测试")
	public String dtv5test() {
		logger.debug("dtv5test");
		return "dtv5test";
	}
	
	@GetMapping("/getNewShijCungnoVO")
	@ApiOperation("获取时间存根号")
	public GetNewShijCungnoVO getNewShijCungno() {
		String cungno = "";
		try{
			cungno = this.yuanService.getNewShijCungno();
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new GetNewShijCungnoVO(e);
		}
		return new GetNewShijCungnoVO(cungno);
	}
	
	@PostMapping("/dtv5Test")
	@ApiOperation("dtv5Test")
	public BaseVO dtv5Test() {
		try {
			this.ywService.sTest();
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ErrorVO(e);
		}		
		return new SuccessVO();
	}

}
