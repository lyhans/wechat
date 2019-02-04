package com.gladfish.work.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.gladfish.common.utils.EncryptionUtil;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.message.WxMpXmlMessage;
import com.gladfish.work.wechat.message.WxMpXmlOutMessage;
import com.gladfish.work.wechat.service.IWechatService;
import me.chanjar.weixin.common.api.WxConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/wechat/portal")
public class PortalController {

	private static final Logger log = LoggerFactory.getLogger(PortalController.class);

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
	@GetMapping
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

	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String post(@RequestBody String requestBody,
					   @RequestParam("signature") String signature,
					   @RequestParam("timestamp") String timestamp,
					   @RequestParam("nonce") String nonce,
					   @RequestParam("openid") String openid,
					   @RequestParam(name = "encrypt_type", required = false) String encType,
					   @RequestParam(name = "msg_signature", required = false) String msgSignature) throws Exception {
		log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				openid, signature, encType, msgSignature, timestamp, nonce, requestBody);

		String out = null;
		if (encType == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage outMessage = WxMpXmlOutMessage.TEXT().toUser(inMessage.getFromUser()).fromUser(inMessage.getToUser()).content("欢迎关注悦鱼科技").build();
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toXml();

			if(WxConsts.EventType.SUBSCRIBE.equals(inMessage.getEvent())){//关注
				wechatService.subscribe(openid);
			}
		} else if ("aes".equalsIgnoreCase(encType)) {
//			// aes加密的消息
//			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxService.getWxMpConfigStorage(),
//					timestamp, nonce, msgSignature);
//			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
//			WxMpXmlOutMessage outMessage = this.route(inMessage, appid);
//			if (outMessage == null) {
//				return "";
//			}
//
//			out = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
		}

		log.info("\n组装回复信息：{}", out);
		return out;
	}

	/**
	 *
	 * @param publicId
	 * @return
	 * @throws BizException
	 */
	@RequestMapping(value = "/getcallbackip.json")
	public List<String> getCallbackIp(@RequestParam Long publicId) throws BizException {
		return wechatService.getCallbackIp();
	}

	/**
	 *
	 * @param code
	 * @throws BizException
	 */
	@RequestMapping("/tologin/userinfo")
	public void check(String code) throws BizException{
		WechatUserInfoForm wechatUserInfoForm = wechatService.getUserInfoByCode(code);
		log.info(JSON.toJSONString(wechatUserInfoForm));
	}

}
