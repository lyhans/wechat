package com.gladfish.work.browse.mapper;

import com.gladfish.work.browse.model.ViewHtmlEntity;

import java.util.List;

public interface ViewHtmlEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ViewHtmlEntity record);

    int insertSelective(ViewHtmlEntity record);

    ViewHtmlEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ViewHtmlEntity record);

    int updateByPrimaryKey(ViewHtmlEntity record);

    ViewHtmlEntity selectByUuid(String uuid);

    List<ViewHtmlEntity> selectByUserId(Long userId);
}