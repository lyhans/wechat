package com.gladfish.work.pubase.service;

import com.gladfish.work.pubase.model.UserEntity;
import com.gladfish.work.wechat.form.UserForm;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/18 0:35
 */
public interface IUserService {

    /**
     * 添加用户
     * @param openid
     * @param name
     * @param sex
     * @param headimg
     * @return
     */
    Long addUserByWechat(String openid,String name,Integer sex,String headimg);

}
