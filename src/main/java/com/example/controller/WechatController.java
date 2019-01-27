package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.base.exception.BizException;
import com.example.common.utils.EncryptionUtil;
import com.example.form.WechatUserInfoForm;
import com.example.service.IWechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wechat")
public class WechatController {

	private static final Logger log = LoggerFactory.getLogger(WechatController.class);

	@Value("${appid}")
	private String appid;

	@Value("${secret}")
	private String secret;

	@Value("${token}")
	private String token;


	@Autowired
	private IWechatService wechatService;

	/**
	 * 微信接入
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/checkSignature.json")
	public String checkSignature(@RequestParam String signature,@RequestParam String timestamp,@RequestParam String nonce,@RequestParam String echostr){
		log.info("checkSignature:signature="+signature+",timestamp="+timestamp+",nonce="+nonce+",echostr="+echostr);
		String[] arr = new String[]{token,timestamp,nonce};
		//字典排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		//sha1加密
		String temp = EncryptionUtil.getSha1(content.toString());
		log.info("checkSignature:temp="+temp+",signature="+signature);
		if (temp.equals(signature)){
			return echostr;
		}
		return "fail";
	}

	@RequestMapping(value = "/getcallbackip.json")
	public List<String> getCallbackIp(@RequestParam Long publicId) throws BizException{
		return wechatService.getCallbackIp(publicId);
	}

	@RequestMapping("/tologin/userinfo")
	public void check(String code) throws BizException{
		WechatUserInfoForm wechatUserInfoForm = wechatService.getUserInfo(2L,code);
		log.info(JSON.toJSONString(wechatUserInfoForm));
	}

}
