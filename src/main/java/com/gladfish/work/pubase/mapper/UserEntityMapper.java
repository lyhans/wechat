package com.gladfish.work.pubase.mapper;

import com.gladfish.work.pubase.model.UserEntity;
import com.gladfish.work.wechat.model.WechatUserEntity;

import java.util.List;

public interface UserEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserEntity record);

    Long insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    List<UserEntity> selectByRelatedId(String relatedId);
}