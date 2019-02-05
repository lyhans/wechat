package com.gladfish.work.wechat.service;

import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.model.WechatUserEntity;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/3 22:06
 */
public interface IWechatUserService {

    /**
     *
     * @param wechatUserInfoForm
     * @return
     */
    String addWechatUser(WechatUserInfoForm wechatUserInfoForm);

    /**
     *
     * @param openid
     * @return
     */
    WechatUserEntity getByOpenid(String openid);

    void update(WechatUserEntity wechatUserEntity);

}
