package com.gladfish.work.wechat.mapper;

import com.gladfish.work.wechat.model.WechatUserEntity;

public interface WechatUserEntityMapper {
    int deleteByPrimaryKey(String id);

    Long insert(WechatUserEntity record);

    void insertSelective(WechatUserEntity record);

    WechatUserEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WechatUserEntity record);

    int updateByPrimaryKey(WechatUserEntity record);
}