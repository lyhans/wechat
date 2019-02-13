package com.gladfish.work.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.gladfish.common.config.ConfigProperties;
import com.gladfish.common.consts.ViewUrl;
import com.gladfish.common.utils.EncryptionUtil;
import com.gladfish.common.utils.LinkUtil;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.wechat.enums.EnumButtonType;
import com.gladfish.work.wechat.form.ButtonForm;
import com.gladfish.work.wechat.form.MenuForm;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.message.WxMpXmlMessage;
import com.gladfish.work.wechat.message.WxMpXmlOutMessage;
import com.gladfish.work.wechat.service.IWechatService;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.api.WxConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wechat/portal")
public class PortalController {

	private static final Logger log = LoggerFactory.getLogger(PortalController.class);

	@Autowired
	private ConfigProperties configProperties;

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
		String[] arr = new String[]{configProperties.getToken(),timestamp,nonce};
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
			if(WxConsts.EventType.UNSUBSCRIBE.equals(inMessage.getEvent())){//取消关注
				wechatService.unsubscribe(openid);
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

	@RequestMapping(value = "/menucreate.json")
	public void menuCreate() throws BizException {
		MenuForm menuForm = new MenuForm();
		ButtonForm buttonForm1 = new ButtonForm();
		buttonForm1.setName("悦鱼官网");
		buttonForm1.setType(EnumButtonType.VIEW);
		buttonForm1.setUrl("http://www.gladfish.cn/");

		ButtonForm buttonForm2 = new ButtonForm();
		buttonForm2.setName("测试功能");
		buttonForm2.setType(EnumButtonType.VIEW);
		Map<String,Object> _params = new HashMap<>();
		_params.put("uri", LinkUtil.createUrl(ViewUrl.CREATE_HTML_URL,_params));
		String url = LinkUtil.createUrl(ViewUrl.USER_INFO_URL,_params);
		buttonForm2.setUrl(url);
//		buttonForm2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx540a3fb596ce9dc5&redirect_uri=http://2349390gv5.imwork.net/browse/showcreateview&response_type=code&scope=snsapi_base");

		menuForm.setButton(Lists.newArrayList(buttonForm1,buttonForm2));
		log.info(JSON.toJSONString(buttonForm2));
		wechatService.menuCreate(menuForm);
//		System.out.println("----------------"+url);
	}

}
