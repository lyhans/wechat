package com.example.service;


import com.example.base.exception.BizException;
import com.example.form.MenuForm;
import com.example.form.WechatUserInfoForm;

import java.util.List;

public interface IWechatService {

	/**
	 * 根据appid更新accessToken
	 * @param publicId
	 * @throws BizException
	 */
	void updateAccessToken(Long publicId)  throws BizException ;

	/**
	 * 获取微信服务器IP地址
	 * @param publicId
	 * @return
	 * @throws BizException
	 */
	List<String> getCallbackIp(Long publicId)  throws BizException ;

	WechatUserInfoForm getUserInfo(Long publicId,String openid)throws BizException ;

	void menuCreate(Long publicId, MenuForm menuForm)throws BizException ;
}
