package com.example.controller;

import com.example.base.exception.BizException;
import com.example.service.IWechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wechat")
public class WechatController {

	private static final Logger log = LoggerFactory.getLogger(JsonController.class);

	@Value("${appid}")
	private String appid;

	@Value("${secret}")
	private String secret;


	@Autowired
	private IWechatService wechatService;

	@RequestMapping(value = "/add.json")
	public void getAccessToken(@RequestParam String name,@RequestParam String appid,@RequestParam String secret)  throws
			BizException {
		wechatService.addPublic(name,appid,secret);
	}
}
