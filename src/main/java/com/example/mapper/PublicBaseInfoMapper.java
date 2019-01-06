package com.example.mapper;

import com.example.model.PublicBaseInfo;

public interface PublicBaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublicBaseInfo record);

    int insertSelective(PublicBaseInfo record);

    PublicBaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublicBaseInfo record);

    int updateByPrimaryKey(PublicBaseInfo record);
}