package com.gladfish.work.pubase.service;

import com.gladfish.work.pubase.model.PublicBaseInfoEntity;

/**
 * <p>公众平台基础服务
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/3 18:13
 */
public interface IPublicBaseInfoService {

    /**
     * 查询悦鱼公众号基础信息
     * @return
     */
    PublicBaseInfoEntity getGladFishPublicBaseInfoEntity();

}
