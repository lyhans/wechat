package com.gladfish.work.pubase.mapper;

import com.gladfish.work.pubase.model.PublicBaseInfoEntity;

public interface PublicBaseInfoEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublicBaseInfoEntity record);

    int insertSelective(PublicBaseInfoEntity record);

    PublicBaseInfoEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublicBaseInfoEntity record);

    int updateByPrimaryKey(PublicBaseInfoEntity record);
}