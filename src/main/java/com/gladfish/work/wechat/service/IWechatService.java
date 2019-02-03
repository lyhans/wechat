package com.gladfish.work.wechat.service;


import com.gladfish.frame.exception.BizException;
import com.gladfish.work.wechat.form.MenuForm;
import com.gladfish.work.wechat.form.WechatUserInfoForm;

import java.util.List;

public interface IWechatService {

	/**
	 * 根据appid更新accessToken
	 * @param publicId
	 * @throws BizException
	 */
	void updateAccessToken(Long publicId)  throws BizException;

	/**
	 * 获取微信服务器IP地址
	 * @param publicId
	 * @return
	 * @throws BizException
	 */
	List<String> getCallbackIp(Long publicId)  throws BizException ;

	WechatUserInfoForm getUserInfo(Long publicId,String code)throws BizException ;

	WechatUserInfoForm getSnsUserInfo(Long publicId,String openid)throws BizException ;

	void menuCreate(Long publicId, MenuForm menuForm)throws BizException ;

}