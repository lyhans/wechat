package com.example.mapper;

import com.example.model.PublicBaseInfoEntity;

public interface PublicBaseInfoEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublicBaseInfoEntity record);

    int insertSelective(PublicBaseInfoEntity record);

    PublicBaseInfoEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublicBaseInfoEntity record);

    int updateByPrimaryKey(PublicBaseInfoEntity record);
}