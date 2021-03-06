package com.gladfish.work.wechat.service;


import com.gladfish.frame.exception.BizException;
import com.gladfish.work.wechat.form.MenuForm;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.form.WxJsapiSignatureForm;

import java.util.List;

public interface IWechatService {

	/**
	 * 根据appid更新accessToken
	 * @param appid
	 * @param secret
	 * @return
	 * @throws BizException
	 */
	String updateAccessToken(String appid,String secret)  throws BizException;

	/**
	 * 获取微信服务器IP地址
	 * @param publicId
	 * @return
	 * @throws BizException
	 */
	List<String> getCallbackIp()  throws BizException ;

	Long getUserIdByCode(String code)throws BizException ;

	WechatUserInfoForm getSnsUserInfoByCode(String code)throws BizException ;

	void menuCreate(MenuForm menuForm)throws BizException ;

	/**
	 * 订阅
	 * @param openid
	 * @return
	 */
	Long subscribe(String openid)  throws BizException ;

	/**
	 * 取消订阅
	 * @param openid
	 * @throws BizException
	 */
	void unsubscribe(String openid)  throws BizException ;

	WxJsapiSignatureForm createJsapiSignature(String url) throws BizException ;

}
