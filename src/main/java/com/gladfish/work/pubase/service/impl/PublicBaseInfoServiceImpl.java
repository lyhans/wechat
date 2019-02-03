package com.gladfish.work.pubase.service.impl;

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
    private Environment env;

    @Autowired
    private PublicBaseInfoEntityMapper publicBaseInfoEntityMapper;

    @Override
    public PublicBaseInfoEntity getGladFishPublicBaseInfoEntity() {
        if(BooleanUtils.isTrue(Boolean.valueOf(env.getProperty("gladfish.isonline")))){
            return publicBaseInfoEntityMapper.selectByPrimaryKey(1L);
        }else{
            return publicBaseInfoEntityMapper.selectByPrimaryKey(2L);
        }
    }
}
