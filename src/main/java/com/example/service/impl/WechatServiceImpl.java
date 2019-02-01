package com.example.service.impl;

import com.example.aop.annotation.WechatServiceAopAnnotation;
import com.example.base.exception.BizException;
import com.example.form.MenuForm;
import com.example.form.WechatOauth2TokenForm;
import com.example.form.WechatUserInfoForm;
import com.example.helper.WechatHelper;
import com.example.mapper.PublicBaseInfoEntityMapper;
import com.example.model.PublicBaseInfoEntity;
import com.example.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WechatServiceImpl implements IWechatService {

	@Autowired
	private PublicBaseInfoEntityMapper publicBaseInfoMapper;

	@Override
	public void updateAccessToken(Long publicId) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		String accessToken = WechatHelper.getWechatToken(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret());
		publicBaseInfoEntity.setAccessToken(accessToken);
		publicBaseInfoMapper.updateByPrimaryKeySelective(publicBaseInfoEntity);
	}

	@WechatServiceAopAnnotation
	@Override
	public List<String> getCallbackIp(Long publicId) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		List<String> ipList = new ArrayList<String>();
		ipList.addAll(WechatHelper.getCallbackIp(publicBaseInfoEntity.getAccessToken()));
		return ipList;
	}

	@Override
	public WechatUserInfoForm getUserInfo(Long publicId, String code) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		WechatOauth2TokenForm wechatOauth2TokenForm = WechatHelper.getOauth2Token(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret(),code);
		return WechatHelper.getSnsUserInfo(wechatOauth2TokenForm.getAccessToken(),wechatOauth2TokenForm.getOpenId());
	}

	@WechatServiceAopAnnotation
	@Override
	public void menuCreate(Long publicId, MenuForm menuForm) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		String accessToken = publicBaseInfoEntity.getAccessToken();
		WechatHelper.menuCreate(accessToken,menuForm);
	}
}
