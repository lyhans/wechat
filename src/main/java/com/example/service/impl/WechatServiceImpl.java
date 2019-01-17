package com.example.service.impl;

import com.example.base.exception.BizException;
import com.example.helper.WechatHelper;
import com.example.model.PublicBaseInfo;
import com.example.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatServiceImpl implements IWechatService {

//	@Autowired
//	private PublicBaseInfoMapper publicBaseInfoMapper;
//
//	@Override public String getAccessToken(String appid, String secret)  throws BizException {
//		return WechatHelper.getWechatToken(appid,secret);
//	}
//
//	@Override public void addPublic(String name, String appid, String secret)  throws BizException  {
//		PublicBaseInfo publicBaseInfo = new PublicBaseInfo();
//		publicBaseInfo.setName(name);
//		publicBaseInfo.setAppid(appid);
//		publicBaseInfo.setSecret(secret);
//		String accessToken = WechatHelper.getWechatToken(appid,secret);
//		publicBaseInfo.setAccessToken(accessToken);
//		publicBaseInfoMapper.insert(publicBaseInfo);
//	}
}
