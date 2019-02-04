package com.gladfish.work.wechat.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSON;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.mapper.WechatUserEntityMapper;
import com.gladfish.work.wechat.model.WechatUserEntity;
import com.gladfish.work.wechat.service.IWechatService;
import com.gladfish.work.wechat.service.IWechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/3 22:16
 */
@Service
public class WechatUserServiceImpl implements IWechatUserService {

    @Autowired
    private WechatUserEntityMapper wechatUserEntityMapper;

    @Override
    public String addWechatUser(WechatUserInfoForm wechatUserInfoForm) {
        WechatUserEntity wechatUserEntity = new WechatUserEntity();
        wechatUserEntity.setId(wechatUserInfoForm.getOpenid());
        wechatUserEntity.setSubscribe(true);
        wechatUserEntity.setNickname(wechatUserInfoForm.getNickname());
        wechatUserEntity.setSex(Integer.valueOf(wechatUserInfoForm.getSex()));
        wechatUserEntity.setCountry(wechatUserInfoForm.getCountry());
        wechatUserEntity.setProvince(wechatUserInfoForm.getProvince());
        wechatUserEntity.setCity(wechatUserInfoForm.getCity());
        wechatUserEntity.setLanguage(wechatUserInfoForm.getLanguage());
        wechatUserEntity.setHeadimgurl(wechatUserInfoForm.getHeadimgurl());
        wechatUserEntity.setSubscribeTime(new Date(wechatUserInfoForm.getSubscribe_time()));
        wechatUserEntity.setUnionid(wechatUserInfoForm.getUnionid());
        wechatUserEntity.setRemark(wechatUserInfoForm.getRemark());
        wechatUserEntity.setGroupid(wechatUserInfoForm.getGroupid());
        wechatUserEntity.setTagidList(JSON.toJSONString(wechatUserInfoForm.getTagid_list()));
        wechatUserEntity.setSubscribeScene(wechatUserInfoForm.getSubscribe_scene());
        wechatUserEntity.setQrScene(wechatUserInfoForm.getQr_scene());
        wechatUserEntity.setQrSceneStr(wechatUserInfoForm.getQr_scene_str());
        wechatUserEntityMapper.insertSelective(wechatUserEntity);
        return wechatUserInfoForm.getOpenid();
    }
}
