package com.yuansong.demo.boot.jtoolstest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yuansong.demo.boot.jtoolstest.controller.VO.CommonVO;
import com.yuansong.demo.boot.jtoolstest.controller.VO.SecretDecryptVO;
import com.yuansong.demo.boot.jtoolstest.controller.VO.SecretEncryptVO;
import com.yuansong.demo.boot.jtoolstest.controller.req.SecretDecryptReq;
import com.yuansong.demo.boot.jtoolstest.controller.req.SecretEncryptReq;
import com.yuansong.tools.secret.ZillionWSDA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags= {"02 Secret"})
@RequestMapping("/secret")
public class SecretController {

	private static final Logger logger = LoggerFactory.getLogger(SecretController.class);
	
	private Gson gson = new Gson();
	
	@GetMapping("/test")
	@ApiOperation("路径测试")
	public CommonVO test() {
		return new CommonVO();
	}
	
	@PostMapping("/encrypt")
	@ApiOperation("加密(Encrype)")
	public SecretEncryptVO encrypt(@RequestBody SecretEncryptReq req) {
		if(req.getPlainText() == null || req.getKey() == null) {
			String msg = "明文或密钥不能为空。【" + this.gson.toJson(req) + "】";
			logger.error(msg);
			return new SecretEncryptVO(-1,msg);
		}
		ZillionWSDA zillionWSDA = new ZillionWSDA();
		String str = "";
		try {
			str = zillionWSDA.EncryptToBase64Format(req.getPlainText(), req.getKey());
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "加密时遇到错误。【" + e.getMessage() +"】";
			return new SecretEncryptVO(-1,errMsg);
		}
		return new SecretEncryptVO(str);
	}
	
	@PostMapping("/decrypt")
	@ApiOperation("解密(Decrypt)")
	public SecretDecryptVO decrypt(@RequestBody SecretDecryptReq req) {
		if(req.getCipherText() == null || req.getKey() == null) {
			String msg = "密文或密钥不能为空。【" + this.gson.toJson(req) + "】";
			logger.error(msg);
			return new SecretDecryptVO(-1,msg);
		}
		ZillionWSDA zillionWSDA = new ZillionWSDA();
		String str = "";
		try {
			str = zillionWSDA.DecryptFromBase64Format(req.getCipherText(), req.getKey());
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "解密时遇到错误。【" + e.getMessage() +"】";
			return new SecretDecryptVO(-1,errMsg);
		}
		return new SecretDecryptVO(str);
	}
	
	//加密
//	EncryptToBase64Format
	//解密
//	DecryptFromBase64Format
}
