package com.example.service;

import com.example.Base.Exception.BizException;

public interface IWechatService {

	/**
	 * 获取access_token
	 * @param appid
	 * @param secret
	 * @return
	 */
	String getAccessToken(String appid,String secret)  throws BizException;

	/**
	 * 新增微信公众号
	 * @param name
	 * @param appid
	 * @param secret
	 */
	void addPublic(String name,String appid,String secret)  throws BizException ;
}
