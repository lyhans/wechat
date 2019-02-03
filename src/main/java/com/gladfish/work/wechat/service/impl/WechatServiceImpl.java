package com.gladfish.work.wechat.service.impl;

import com.gladfish.frame.aop.annotation.WechatServiceAopAnnotation;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.wechat.form.MenuForm;
import com.gladfish.work.wechat.form.WechatOauth2TokenForm;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.support.helper.WechatHelper;
import com.gladfish.work.pubase.mapper.PublicBaseInfoEntityMapper;
import com.gladfish.work.pubase.model.PublicBaseInfoEntity;
import com.gladfish.work.wechat.service.IWechatService;
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

	@WechatServiceAopAnnotation
	@Override
	public WechatUserInfoForm getUserInfo(Long publicId, String code) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		WechatOauth2TokenForm wechatOauth2TokenForm = WechatHelper.getOauth2Token(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret(),code);
		return WechatHelper.getUserInfo(publicBaseInfoEntity.getAccessToken(),wechatOauth2TokenForm.getOpenId());
	}

	@WechatServiceAopAnnotation
	@Override
	public void menuCreate(Long publicId, MenuForm menuForm) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		String accessToken = publicBaseInfoEntity.getAccessToken();
		WechatHelper.menuCreate(accessToken,menuForm);
	}

	@WechatServiceAopAnnotation
	@Override
	public WechatUserInfoForm getSnsUserInfo(Long publicId, String openid) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(publicId);
		WechatOauth2TokenForm wechatOauth2TokenForm = WechatHelper.getOauth2Token(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret(),openid);
		return WechatHelper.getSnsUserInfo(wechatOauth2TokenForm.getAccessToken(),wechatOauth2TokenForm.getOpenId());
	}
}
