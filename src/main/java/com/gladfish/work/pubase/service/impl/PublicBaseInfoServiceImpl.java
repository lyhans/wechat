package com.gladfish.work.pubase.service.impl;

import com.gladfish.common.config.ConfigProperties;
import com.gladfish.work.pubase.mapper.PublicBaseInfoEntityMapper;
import com.gladfish.work.pubase.model.PublicBaseInfoEntity;
import com.gladfish.work.pubase.service.IPublicBaseInfoService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/3 18:17
 */
@Service
public class PublicBaseInfoServiceImpl implements IPublicBaseInfoService {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private PublicBaseInfoEntityMapper publicBaseInfoEntityMapper;

    @Override
    public PublicBaseInfoEntity getGladFishPublicBaseInfoEntity() {
        if(BooleanUtils.isTrue(Boolean.valueOf(configProperties.getIsonline()))){
            return publicBaseInfoEntityMapper.selectByPrimaryKey(1L);
        }else{
            return publicBaseInfoEntityMapper.selectByPrimaryKey(2L);
        }
    }

    @Override
    public void update(PublicBaseInfoEntity publicBaseInfoEntity) {
        publicBaseInfoEntityMapper.updateByPrimaryKeySelective(publicBaseInfoEntity);
    }
}
