package com.example.mapper;

import com.example.model.RoleAccount;

public interface RoleAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleAccount record);

    int insertSelective(RoleAccount record);

    RoleAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAccount record);

    int updateByPrimaryKey(RoleAccount record);
}