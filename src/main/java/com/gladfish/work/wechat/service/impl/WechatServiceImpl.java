package com.gladfish.work.wechat.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gladfish.common.enums.EnumErrorCode;
import com.gladfish.frame.aop.annotation.WechatServiceAopAnnotation;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.pubase.model.UserEntity;
import com.gladfish.work.pubase.service.IPublicBaseInfoService;
import com.gladfish.work.pubase.service.IUserService;
import com.gladfish.work.wechat.form.MenuForm;
import com.gladfish.work.wechat.form.WechatOauth2TokenForm;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.support.helper.WechatHelper;
import com.gladfish.work.pubase.mapper.PublicBaseInfoEntityMapper;
import com.gladfish.work.pubase.model.PublicBaseInfoEntity;
import com.gladfish.work.wechat.form.WxJsapiSignatureForm;
import com.gladfish.work.wechat.model.WechatUserEntity;
import com.gladfish.work.wechat.service.IWechatService;
import com.gladfish.work.wechat.service.IWechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WechatServiceImpl implements IWechatService {

	@Autowired
	private IWechatUserService  wechatUserService;

	@Autowired
	private IPublicBaseInfoService publicBaseInfoService;

	@Autowired
	private IUserService userService;


	@Override
	public String updateAccessToken(String appid, String secret) throws BizException {
		return WechatHelper.getWechatToken(appid,secret);
	}

	@WechatServiceAopAnnotation
	@Override
	public List<String> getCallbackIp() throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		List<String> ipList = new ArrayList<String>();
		ipList.addAll(WechatHelper.getCallbackIp(publicBaseInfoEntity.getAccessToken()));
		return ipList;
	}

	@WechatServiceAopAnnotation
	@Override
	public Long getUserIdByCode(String code) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		WechatOauth2TokenForm wechatOauth2TokenForm = WechatHelper.getOauth2Token(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret(),code);
		WechatUserEntity wechatUserEntity = wechatUserService.getByOpenid(wechatOauth2TokenForm.getOpenId());
		if(wechatUserEntity == null || wechatUserEntity.getSubscribe() == false){
			throw new BizException(EnumErrorCode.UNSUBSCRIBE);
		}
		return userService.getByRelatedId(wechatOauth2TokenForm.getOpenId()).get(0).getId();
	}

	@WechatServiceAopAnnotation
	@Override
	public void menuCreate(MenuForm menuForm) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		String accessToken = publicBaseInfoEntity.getAccessToken();
		WechatHelper.menuCreate(accessToken,menuForm);
	}

	@WechatServiceAopAnnotation
	@Override
	public WechatUserInfoForm getSnsUserInfoByCode(String code) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		WechatOauth2TokenForm wechatOauth2TokenForm = WechatHelper.getOauth2Token(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret(),code);
		return WechatHelper.getSnsUserInfo(wechatOauth2TokenForm.getAccessToken(),wechatOauth2TokenForm.getOpenId());
	}

	@WechatServiceAopAnnotation
	@Override
	public Long subscribe(String openid)  throws BizException  {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		WechatUserInfoForm wechatUserInfoForm = WechatHelper.getUserInfo(publicBaseInfoEntity.getAccessToken(),openid);
		WechatUserEntity wechatUserEntity = wechatUserService.getByOpenid(openid);
		if(wechatUserEntity == null) {
			wechatUserService.addWechatUser(wechatUserInfoForm);
			return userService.addUserByWechat(wechatUserInfoForm.getOpenid(),
					wechatUserInfoForm.getNickname(), Integer.valueOf(wechatUserInfoForm.getSex()),
					wechatUserInfoForm.getHeadimgurl());
		}else{
			wechatUserEntity.setSubscribe(true);
			wechatUserService.update(wechatUserEntity);
			List<UserEntity> userEntities = userService.getByRelatedId(openid);
			return userEntities.get(0).getId();
		}
	}

	@Override
	public void unsubscribe(String openid) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		WechatUserEntity wechatUserEntity = wechatUserService.getByOpenid(openid);
		if(wechatUserEntity == null){
			throw new BizException("该用户尚未订阅");
		}
		wechatUserEntity.setSubscribe(false);
		wechatUserService.update(wechatUserEntity);
	}

	@WechatServiceAopAnnotation
	@Override
	public WxJsapiSignatureForm createJsapiSignature(String url) throws BizException {
		PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
		String jsapiTicket = WechatHelper.getTicket(publicBaseInfoEntity.getAccessToken());
		return WechatHelper.createJsapiSignature(publicBaseInfoEntity.getAppid(),jsapiTicket,url);
	}
}
